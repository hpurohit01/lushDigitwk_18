package com.localhost.productsinfo;

import com.localhost.model.ProductPojo;
import com.localhost.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TestInfo extends TestBase {

    //Get Products

    @Test
    public void searchProductList() {

        Response response = given()
                .when()
                .get("/products");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void searchSingleProduct() {
        Response response = given()

                .basePath("/products")
                .pathParam("id", 2)
                .when()
                .get("{/id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void createProductWithPost() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("Apricot jam");
        productPojo.setAvailable(100);




        Response response = given()
                //.header("Content-Type", "application/json")
                .body(productPojo)
                .when()
                .post("/products");
        response.then().statusCode(200);
        response.prettyPrint();
//        {
//            "status": "ok",
//                "code": 200,
//                "message": "",
//                "data": {
//            "products": {
//                "id": 5,
//                        "name": "Apricot jam",
//                        "available": 100,
//                        "created_at": "2022-01-04T13:11:38.6762544Z",
//                        "updated_at": "2022-01-04T13:11:38.6762544Z"
//            }
//        }
    }

    @Test
    public void createProductwithPut() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("Apple Jam");
        productPojo.setAvailable(10);

        Response response = given()
                .basePath("/products")
                .header("Content-Type", "application/json")
                .pathParam("id", 5)
                .body(productPojo)
                .when()
                .put("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();

//            "status": "ok",
//                "code": 200,
//                "message": "",
//                "data": {
//            "products": [
//            {
//                "id": 1,
//                    "name": "Strawberry Jam 🍓",
//                    "available": 100,
//                    "created_at": "2018-07-30T09:18:37Z",
//                    "updated_at": null
//            },
//            {
//                "id": 2,
//                    "name": "Marmalade 🍊",
//                    "available": 5,
//                    "created_at": "2018-08-16T14:38:42Z",
//                    "updated_at": null
//            },
//            {
//                "id": 3,
//                    "name": "Lemon Curd 🍋",
//                    "available": 30,
//                    "created_at": "2018-09-01T17:52:07Z",
//                    "updated_at": null
//            },
//            {
//                "id": 0,
//                    "name": "Apple Jam",
//                    "available": 10,
//                    "created_at": "2022-01-04T13:11:38.6762544Z",
//                    "updated_at": "2022-01-04T13:14:16.1892959Z"
//            },
//            {
//                "id": 0,
//                    "name": "Apple Jam",
//                    "available": 10,
//                    "created_at": "2022-01-04T13:11:38.6762544Z",
//                    "updated_at": "2022-01-04T13:14:16.1892959Z"
//            }
//        ]
//        }
//        }
    }
    @Test
    public void ProductwithPatch() {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName("Mixed Fruit Jam");

        Response response = given()
                //   .basePath("/products")
                .header("Content-Type", "application/json")
                .pathParam("id", 5)

                .body(productPojo)
                .when()
                .patch("/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test
    public void deleteProduct() {
        Response response = given()
                .pathParam("id", 3)
                .header("Content-Type", "application/json")
                .when()
                .delete("/{id}");
        response.then().statusCode(404);
    }
}



