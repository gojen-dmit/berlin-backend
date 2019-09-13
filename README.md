# berlin-backend

Cloud Native Development Experience 

## Build with template from catalog

Build on OpenShift using OpenJDK 8 Template from catalog. Requires running PostgreSQL db in project.

## Build with Jenkins

```bash
oc new-build https://github.com/bpg-it/berlin-backend.git
```

## Build with build/dev/int/prod stages

Please have a look at [https://github.com/bpg-it/berlin-backend-openshift](https://github.com/bpg-it/berlin-backend-openshift).
