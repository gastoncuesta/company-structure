
# Company Structure

This application just get all descendants of a member and change any parent of a member

## Author

[Gastón Cuesta](https://www.linkedin.com/in/gastoncuesta/)


## API Reference

#### Get all descendant nodes of a given node

```http
  GET /structure/descendants/{memberId}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `memberId` | `long` | **Required**. Id of a Member |

#### Change the parent node of a given node

```http
  POST /structure/changeParent
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `memberId` | `long` | **Required**. Id of a Child Member |
| `newParentId`| `long` | **Required**. Id of a Parent Member|


