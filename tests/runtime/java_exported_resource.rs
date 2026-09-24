use wasmtime::component::ResourceAny;
use wasmtime::Store;

wasmtime::component::bindgen!(in "tests/runtime/java_exported_resource");

use exports::test::java_exported_resource::test::Guest;

#[test]
fn run() -> anyhow::Result<()> {
    crate::run_test(
        "java_exported_resource",
        |_| Ok(()),
        |store, component, linker| {
            Ok(JavaExportedResource::instantiate(store, component, linker)?.interface0)
        },
        run_test,
    )
}

fn run_test(
    instance: Guest,
    store: &mut Store<crate::Wasi<()>>,
) -> anyhow::Result<()> {
    let resource = instance.item().call_constructor(&mut *store, 7)?;
    assert_eq!(instance.item().call_get(&mut *store, resource)?, 7);

    instance.item().call_set(&mut *store, resource, 11)?;
    assert_eq!(instance.item().call_get(&mut *store, resource)?, 11);

    ResourceAny::resource_drop(resource, &mut *store)?;
    assert!(ResourceAny::resource_drop(resource, &mut *store).is_err());

    Ok(())
}
