# JDBC Mapper

##  Purpose

Mainly this is created for the need to remove the ResultSetMapper  which we need to use when we get the result from the JDBCTemplate result set. It can also be used to create map from the class.
## What it does?

This library is used to map java object to map and vice versa. It supports mapping of primitive type, map and java object. It also supports custom mapping where you can define your own method to map certain value with some validation or logic.

For the source code reference on how to use it you can use test class but main syntax are as below.

## How to use it?
To map the database column to any of the variable you need @FieldId annotation. If you want to use the custom mapping you need to pass @CustomMapper annotation which would need package name, function name and if method is static or not.

Following will map the database column BYTE_VALUE to the variable byteValue.
```java
@FieldId("BYTE_VALUE")
private byte byteValue;
```

Following will map the database column CUSTOM_VALUE to the variable customValue by using the method that is specified in custom mapper class.
```java 
@FieldId(value = "CUSTOM_VALUE", customMapper = @CustomMapper(className = "io.github.karanbhatt100.tools.jdbc_mapper.util.UtilFunc", methodName = "customMapperMethod", isStatic = false))
private String customValue;
```

### Map to class
Following will convert map to the Result class instance
```java 
Result mapResult = MapClasser.fromMap(result, Result.class);
```

You can also use list of map to convert to list of Result class instance
```java
List<Result> mapResultList = MapClasser.fromMapViaVariable(List.of(result), Result.class);
```

If you don't want to use the @FieldId and do the mapping by variable then you can use following.
```java
Result mapResult = MapClasser.fromMapViaVariable(result, Result.class);
List<Result> mapResultList = MapClasser.fromMapViaVariable(List.of(result), Result.class);
```

### Class to map

Following will convert class to the map.
```java 
Map<String, Object> value = ClassMapper.toMap(result);
```

You can also use list of map to convert to list of Result class instance
```java
List<Map<String, Object>> valueList = ClassMapper.toMap(List.of(result));
```

If you don't want to use the @FieldId and do the mapping by variable then you can use following.
```java
Map<String, Object> value = ClassMapper.toMapViaVariable(result);
List<Map<String, Object>> valueList = ClassMapper.toMapViaVariable(List.of(result));
```