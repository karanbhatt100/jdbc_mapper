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

## Average time calculation
# Release 1.0
Average time to create list of size 1 is 1481097240.0 in timeInNano
Average time to create list of size 2 is 1482473920.0 in timeInNano
Average time to create list of size 4 is 1498339760.0 in timeInNano
Average time to create list of size 8 is 1448431220.0 in timeInNano
Average time to create list of size 16 is 1491154200.0 in timeInNano
Average time to create list of size 32 is 1503094380.0 in timeInNano
Average time to create list of size 64 is 1505589900.0 in timeInNano
Average time to create list of size 128 is 1595871920.0 in timeInNano
Average time to create list of size 256 is 1564325900.0 in timeInNano
Average time to create list of size 512 is 1583432660.0 in timeInNano
Average time to create list of size 1024 is 1466816260.0 in timeInNano
Average time to create list of size 2048 is 1554808340.0 in timeInNano
Average time to create list of size 4096 is 1528872960.0 in timeInNano
Average time to create list of size 8192 is 1541723180.0 in timeInNano
Average time to create list of size 16384 is 1571499560.0 in timeInNano
Average time to create list of size 32768 is 1595847320.0 in timeInNano
Average time to create list of size 65536 is 1524846920.0 in timeInNano
Average time to create list of size 131072 is 1519164740.0 in timeInNano
Average time to create list of size 262144 is 1428613360.0 in timeInNano
Average time to create list of size 524288 is 1489660980.0 in timeInNano