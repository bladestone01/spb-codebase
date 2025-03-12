### **通用注解说明**
| **注解**             | **适用方法**      | **功能说明**                                                                 |
|-----------------------|-------------------|-----------------------------------------------------------------------------|
| `@RequestParam`       | GET/POST/DELETE   | 获取URL中的**查询参数**（如`?key=value`），支持默认值和必填校验。           |
| `@PathVariable`       | GET/PUT/DELETE    | 从URL路径中**提取变量**（如`/user/{id}`）。                                 |
| `@RequestBody`        | POST/PUT          | 将**请求体中的JSON/XML数据**绑定到Java对象（需配合`Content-Type: application/json`）。 |
| `@ModelAttribute`     | GET/POST          | 绑定**表单数据**到对象，或用于向视图传递模型数据。                          |
| `@Valid`              | POST/PUT          | 与JSR-303规范结合，对请求参数或对象**进行数据校验**（如`@NotNull`）。        |
| `@RequestPart`        | POST              | 处理`multipart/form-data`请求中的**文件上传**（如上传图片）。               |
| `@RequestHeader`     | 所有方法          | 获取**HTTP请求头**中的值（如`@RequestHeader("User-Agent") String userAgent`）。 |
| `@CookieValue`       | 所有方法          | 获取**Cookie**中的值（如`@CookieValue("sessionId") String sessionId`）。    |
| `@ResponseBody`       | 所有方法          | 将方法返回值**直接写入HTTP响应体**（用于RESTful API，替代视图渲染）。       |

---

### **注意事项**
1. **`@RequestBody` 限制**：
- 仅适用于POST/PUT方法，且**不能用于GET请求**（GET无请求体）。
- 需在请求头中指定`Content-Type: application/json`。

2. **`@ModelAttribute` vs `@RequestBody`**：
- `@ModelAttribute`：处理`x-www-form-urlencoded`或`form-data`格式的表单数据。
- `@RequestBody`：处理结构化数据（如JSON/XML）。

3. **RESTful API 推荐组合**：
- 使用`@RequestBody` + `@ResponseBody` + 专用方法注解（如`@PostMapping`）。
- 示例：
```java
@RestController
@RequestMapping("/api")
public class UserController {
@PostMapping("/users")
public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
    // 创建用户逻辑
    return ResponseEntity.ok(savedUser);
    }
    }
    ```

    4. **参数绑定优先级**：
    - 若同时使用`@RequestParam`和`@PathVariable`，参数名需与URL或查询字符串中的名称一致。
