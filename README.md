# BProc REST Sample

## Overview

The project demonstrates how to execute BProc actions using the REST API (from the mobile app, for example).

The project contains a middleware service ([ExternalBprocService](modules/global/src/com/company/sample/service/ExternalBprocService.java)) for few basic BProc operations (start a process, get a list of user tasks, complete a user task). That service is configured for REST API (see the [rest-services.xml](modules/web/src/com/company/sample/rest-services.xml)). Service methods return meta classes of the BProc addon. If you don't need all the fields of these classes in the response or want responses to be in custom format then you can build and return simple POJOs from service methods. POJOs will be automatically serialized to JSON.

The [test-process-1.bpmn20.xml](bpmn/test-process-1.bpmn20.xml) is a sample process that can be deployed for testing. It contains two user tasks, both of them will be assigned to **admin** using the [TestUserProvider](modules/core/src/com/company/sample/core/TestUserProvider.java). Forms are configured for start node and user tasks. 

When user tasks must be completed from external clients only (mobile apps, custom portals, etc.), then you may think about using [custom process forms](https://doc.cuba-platform.com/bproc-1.1/#custom-process-form).

The [bproc-rest.http](http/bproc-rest.http) contains examples of HTTP requests.

## Useful Links

[BProc API](https://doc.cuba-platform.com/bproc-1.1#bproc-api)

[BProc Custom Process Forms](https://doc.cuba-platform.com/bproc-1.1/#custom-process-form)

[REST API Services Configuration](https://doc.cuba-platform.com/restapi-7.2/#rest_api_v2_services_config)

[REST API Service Method Invocation (POST)](https://doc.cuba-platform.com/restapi-7.2/#rest_api_v2_ex_service_post)