# Quarkus Issue  #35577
Reproducer for https://github.com/quarkusio/quarkus/issues/35577

Use Java 17

## This works:

```shell script
./gradlew build
java -jar app1/build/quarkus-app/quarkus-run.jar
```

Visit http://localhost:8080/

Output:
```
Messages:
This is app 1
Timestamp 1693048576060
-- end
```

## Dev mode is broken:


```shell script
./gradlew app1:quarkusDev
```

Error during startup:

```
2023-08-26 15:19:27,482 INFO  [io.qua.dep.dev.IsolatedDevModeMain] (main) Attempting to start live reload endpoint to recover from previous Quarkus startup failure
> :ap2023-08-26 15:19:28,022 ERROR [io.qua.dep.dev.IsolatedDevModeMain] (main) Failed to start quarkus: java.lang.RuntimeException: io.quarkus.builder.BuildException: Build failure: Build failed due to errors
        [error]: Build step io.quarkus.arc.deployment.ArcProcessor#validate threw an exception: jakarta.enterprise.inject.spi.DeploymentException: jakarta.enterprise.inject.UnsatisfiedResolutionException: Unsatisfied dependency for type org.acme.core.kt.ConfigService and qualifiers [@Default]
        - java member: org.acme.app1.App1Controller#config
        - declared on CLASS bean [types=[org.acme.app1.App1Controller, java.lang.Object], qualifiers=[@Default, @Any], target=org.acme.app1.App1Controller]
        at io.quarkus.arc.processor.BeanDeployment.processErrors(BeanDeployment.java:1447)
        at io.quarkus.arc.processor.BeanDeployment.init(BeanDeployment.java:311)
        at io.quarkus.arc.processor.BeanProcessor.initialize(BeanProcessor.java:158)
        at io.quarkus.arc.deployment.ArcProcessor.validate(ArcProcessor.java:469)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:568)
        at io.quarkus.deployment.ExtensionLoader$3.execute(ExtensionLoader.java:858)
        at io.quarkus.builder.BuildContext.run(BuildContext.java:282)
        at org.jboss.threads.ContextHandler$1.runWith(ContextHandler.java:18)
        at org.jboss.threads.EnhancedQueueExecutor$Task.run(EnhancedQueueExecutor.java:2513)
        at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1538)
        at java.base/java.lang.Thread.run(Thread.java:833)
        at org.jboss.threads.JBossThread.run(JBossThread.java:501)
Caused by: jakarta.enterprise.inject.UnsatisfiedResolutionException: Unsatisfied dependency for type org.acme.core.kt.ConfigService and qualifiers [@Default]
        - java member: org.acme.app1.App1Controller#config
        - declared on CLASS bean [types=[org.acme.app1.App1Controller, java.lang.Object], qualifiers=[@Default, @Any], target=org.acme.app1.App1Controller]
        at io.quarkus.arc.processor.Beans.resolveInjectionPoint(Beans.java:477)
        at io.quarkus.arc.processor.BeanInfo.init(BeanInfo.java:624)
        at io.quarkus.arc.processor.BeanDeployment.init(BeanDeployment.java:299)
        ... 13 more

        at io.quarkus.runner.bootstrap.AugmentActionImpl.runAugment(AugmentActionImpl.java:336)
        at io.quarkus.runner.bootstrap.AugmentActionImpl.createInitialRuntimeApplication(AugmentActionImpl.java:253)
        at io.quarkus.runner.bootstrap.AugmentActionImpl.createInitialRuntimeApplication(AugmentActionImpl.java:60)
        at io.quarkus.deployment.dev.IsolatedDevModeMain.firstStart(IsolatedDevModeMain.java:82)
        at io.quarkus.deployment.dev.IsolatedDevModeMain.accept(IsolatedDevModeMain.java:423)
        at io.quarkus.deployment.dev.IsolatedDevModeMain.accept(IsolatedDevModeMain.java:55)
        at io.quarkus.bootstrap.app.CuratedApplication.runInCl(CuratedApplication.java:138)
        at io.quarkus.bootstrap.app.CuratedApplication.runInAugmentClassLoader(CuratedApplication.java:93)
        at io.quarkus.deployment.dev.DevModeMain.start(DevModeMain.java:131)
        at io.quarkus.deployment.dev.DevModeMain.main(DevModeMain.java:62)
Caused by: io.quarkus.builder.BuildException: Build failure: Build failed due to errors
        [error]: Build step io.quarkus.arc.deployment.ArcProcessor#validate threw an exception: jakarta.enterprise.inject.spi.DeploymentException: jakarta.enterprise.inject.UnsatisfiedResolutionException: Unsatisfied dependency for type org.acme.core.kt.ConfigService and qualifiers [@Default]
        - java member: org.acme.app1.App1Controller#config
        - declared on CLASS bean [types=[org.acme.app1.App1Controller, java.lang.Object], qualifiers=[@Default, @Any], target=org.acme.app1.App1Controller]
        at io.quarkus.arc.processor.BeanDeployment.processErrors(BeanDeployment.java:1447)
        at io.quarkus.arc.processor.BeanDeployment.init(BeanDeployment.java:311)
        at io.quarkus.arc.processor.BeanProcessor.initialize(BeanProcessor.java:158)
        at io.quarkus.arc.deployment.ArcProcessor.validate(ArcProcessor.java:469)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:568)
        at io.quarkus.deployment.ExtensionLoader$3.execute(ExtensionLoader.java:858)
        at io.quarkus.builder.BuildContext.run(BuildContext.java:282)
        at org.jboss.threads.ContextHandler$1.runWith(ContextHandler.java:18)
        at org.jboss.threads.EnhancedQueueExecutor$Task.run(EnhancedQueueExecutor.java:2513)
        at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1538)
        at java.base/java.lang.Thread.run(Thread.java:833)
        at org.jboss.threads.JBossThread.run(JBossThread.java:501)
Caused by: jakarta.enterprise.inject.UnsatisfiedResolutionException: Unsatisfied dependency for type org.acme.core.kt.ConfigService and qualifiers [@Default]
        - java member: org.acme.app1.App1Controller#config
        - declared on CLASS bean [types=[org.acme.app1.App1Controller, java.lang.Object], qualifiers=[@Default, @Any], target=org.acme.app1.App1Controller]
        at io.quarkus.arc.processor.Beans.resolveInjectionPoint(Beans.java:477)
        at io.quarkus.arc.processor.BeanInfo.init(BeanInfo.java:624)
        at io.quarkus.arc.processor.BeanDeployment.init(BeanDeployment.java:299)
        ... 13 more

        at io.quarkus.builder.Execution.run(Execution.java:123)
        at io.quarkus.builder.BuildExecutionBuilder.execute(BuildExecutionBuilder.java:79)
        at io.quarkus.deployment.QuarkusAugmentor.run(QuarkusAugmentor.java:160)
        at io.quarkus.runner.bootstrap.AugmentActionImpl.runAugment(AugmentActionImpl.java:332)
        ... 9 more
Caused by: jakarta.enterprise.inject.spi.DeploymentException: jakarta.enterprise.inject.UnsatisfiedResolutionException: Unsatisfied dependency for type org.acme.core.kt.ConfigService and qualifiers [@Default]
        - java member: org.acme.app1.App1Controller#config
        - declared on CLASS bean [types=[org.acme.app1.App1Controller, java.lang.Object], qualifiers=[@Default, @Any], target=org.acme.app1.App1Controller]
        at io.quarkus.arc.processor.BeanDeployment.processErrors(BeanDeployment.java:1447)
        at io.quarkus.arc.processor.BeanDeployment.init(BeanDeployment.java:311)
        at io.quarkus.arc.processor.BeanProcessor.initialize(BeanProcessor.java:158)
        at io.quarkus.arc.deployment.ArcProcessor.validate(ArcProcessor.java:469)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
        at java.base/java.lang.reflect.Method.invoke(Method.java:568)
        at io.quarkus.deployment.ExtensionLoader$3.execute(ExtensionLoader.java:858)
        at io.quarkus.builder.BuildContext.run(BuildContext.java:282)
        at org.jboss.threads.ContextHandler$1.runWith(ContextHandler.java:18)
        at org.jboss.threads.EnhancedQueueExecutor$Task.run(EnhancedQueueExecutor.java:2513)
        at org.jboss.threads.EnhancedQueueExecutor$ThreadBody.run(EnhancedQueueExecutor.java:1538)
        at java.base/java.lang.Thread.run(Thread.java:833)
        at org.jboss.threads.JBossThread.run(JBossThread.java:501)
Caused by: jakarta.enterprise.inject.UnsatisfiedResolutionException: Unsatisfied dependency for type org.acme.core.kt.ConfigService and qualifiers [@Default]
        - java member: org.acme.app1.App1Controller#config
        - declared on CLASS bean [types=[org.acme.app1.App1Controller, java.lang.Object], qualifiers=[@Default, @Any], target=org.acme.app1.App1Controller]
        at io.quarkus.arc.processor.Beans.resolveInjectionPoint(Beans.java:477)
        at io.quarkus.arc.processor.BeanInfo.init(BeanInfo.java:624)
        at io.quarkus.arc.processor.BeanDeployment.init(BeanDeployment.java:299)
        ... 13 more

```