# Parking Lot 练习题

## 简介

Parking Lot 是 OO BootCamp 的练习题。

* 第一步：停车和取车
* 第二步：停车小弟管理着两个停车场：第一个停满之后，停第二个
* 第三步：停车小弟管理着两个停车场：停到空位最多的停车场
* 第四步：停车小弟管理着两个停车场：停到空位最少的停车场
* 第五步：停车场管理员，他可以管理停车场和小弟
* 第六步：停车场超级管理员，他可以管理停车场和停车场管理员或小弟
* 第七步：打印停车场、停车小弟、停车管理员以及超级管理员的状态报告，展示其管理的详细车位情况

## 基本用法

* 生成 IDEA 工程

```shell
./gradlew idea
```

* 检查

```shell
./gradlew check
```

* 数据库迁移

```shell
./gradlew migrate
```

* 生成构建产物

```shell
./gradlew build
```

* 生成发布包

对于 CLI 项目，运行如下命令
```shell
./gradlew uberJar
```

在 todo-cli/build/libs 下就会生成一个 Uber JAR，它是可以独立运行的。

```shell
java -jar todo-uber-<version>.jar
```
