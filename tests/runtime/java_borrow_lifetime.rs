use wasmtime::{component::Resource, Store};

wasmtime::component::bindgen!(in "tests/runtime/java_borrow_lifetime");

use test::java_borrow_lifetime::host::{Host, HostItem, Item as HostItemType};

#[derive(Default)]
pub struct MyHostItem {
    pings: u32,
    drops: u32,
}

impl Host for MyHostItem {}

impl HostItem for MyHostItem {
    fn ping(&mut self, self_: Resource<HostItemType>) -> u32 {
        assert_eq!(self_.rep(), 7);
        self.pings += 1;
        123
    }

    fn drop(&mut self, self_: Resource<HostItemType>) -> wasmtime::Result<()> {
        assert_eq!(self_.rep(), 7);
        self.drops += 1;
        Ok(())
    }
}

fn instantiate(
    store: &mut Store<crate::Wasi<MyHostItem>>,
    component: &wasmtime::component::Component,
    linker: &wasmtime::component::Linker<crate::Wasi<MyHostItem>>,
) -> anyhow::Result<JavaBorrowLifetime> {
    JavaBorrowLifetime::instantiate(store, component, linker)
}

#[test]
fn test_a_borrowed_resource_is_released() -> anyhow::Result<()> {
    crate::run_test_from_dir(
        "java_borrow_lifetime",
        "java_borrow_lifetime_test_a",
        |linker| JavaBorrowLifetime::add_to_linker(linker, |x| &mut x.0),
        instantiate,
        |instance, store| {
            instance.call_test_a(&mut *store, Resource::new_own(7))?;
            assert_eq!(store.data().0.pings, 0);
            assert_eq!(store.data().0.drops, 0);
            Ok(())
        },
    )
}

#[test]
fn test_b_borrowed_resource_method_is_released() -> anyhow::Result<()> {
    crate::run_test_from_dir(
        "java_borrow_lifetime",
        "java_borrow_lifetime_test_b",
        |linker| JavaBorrowLifetime::add_to_linker(linker, |x| &mut x.0),
        instantiate,
        |instance, store| {
            assert_eq!(instance.call_test_b(&mut *store, Resource::new_own(7))?, 123);
            assert_eq!(store.data().0.pings, 1);
            assert_eq!(store.data().0.drops, 0);
            Ok(())
        },
    )
}
