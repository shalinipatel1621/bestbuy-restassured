package com.bestbuy.testsuite;

import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public static void response() {
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
        response.log().all();
    }

    // 1. Extract the limit
    @Test
    public void test001() {

        int limit = response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + limit);
        System.out.println("------------------End of Test---------------------------");

    }

    //2. Extract the total
    @Test
    public void test002() {

        int total = response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " + total);
        System.out.println("------------------End of Test---------------------------");

    }

    //3. Extract the name of 5th store
    @Test
    public void test003() {

        String storeName = response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }


    //4. Extract the names of all the store
    @Test
    public void test004() {
        List<String> allStoreName = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of store names are : " + allStoreName);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the storeId of all the store
    @Test
    public void test005() {
        List<String> allStoreId = response.extract().path("data.storeId");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of store ID are : " + allStoreId);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Print the size of the data list
    @Test
    public void test006() {
        List<String> sizeOfData = response.extract().path("data");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The size of the data is : " + sizeOfData.size());
        System.out.println("------------------End of Test---------------------------");
    }


    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void test007() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name== 'St Cloud'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of the store are : " + values);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the address of the store where store name = Rochester
    @Test
    public void test008() {
        //  List<HashMap<String, ?>> value = response.extract().path("data.findAll{it.name== 'Rochester'}.address");
        String addressOfRochester = response.extract().path("data[8].address");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + addressOfRochester);
        System.out.println("------------------End of Test---------------------------");
    }


    //9. Get all the services of 8th store
    @Test
    public void test009() {
        List<String> services = response.extract().path("data[7].services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The services are : " + services);
        System.out.println("------------------End of Test---------------------------");
    }


    //10. Get storeservices of the store where service name = Windows Store
    @Test
    public void test010() {
        List<?> services = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Created At : " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get all the storeId of all the store
    @Test
    public void test011() {

        List<Integer> storeIdAll = response.extract().path("data.services.storeservices.storeId");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list of the storeId are : " + storeIdAll);
        System.out.println("------------------End of Test---------------------------");
    }


    //12. Get id of all the store
    @Test
    public void test012() {
        List<Integer> storeId = response.extract().path("data.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + storeId);
        System.out.println("------------------End of Test---------------------------");

    }


    //13. Find the store names Where state = ND
    @Test
    public void test013() {
        List<String> storeName = response.extract().path("data.findAll{it.name== 'ND'.name}");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " + storeName);
        System.out.println("------------------End of Test---------------------------");

    }


    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void test014() {
        List<Integer> storeName = response.extract().path("data.findAll{it.name== 'Rochester'}.services");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + storeName);
        System.out.println("------------------End of Test---------------------------");

    }

    //15. Find the createdAt for all services whose name = “Windows Store”
    @Test
    public void test015() {
        List<?> services = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.storeservices.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Created At : " + services);
        System.out.println("------------------End of Test---------------------------");
    }

    // 16. Find the name of all services Where store name = “Fargo”
    @Test
    public void test016() {
        List<?> services = response.extract().path("data.findAll{it.name=='Fargo'}.services.name");
        // String servicesName = response.extract().path("data[7].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value is : " + services);
        System.out.println("------------------End of Test---------------------------");

    }

    // 17. Find the zip of all the store
    @Test
    public void test017() {
        List<String> zip = response.extract().path("data.zip");
        // String servicesName = response.extract().path("data[7].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of all store are : " + zip);
        System.out.println("------------------End of Test---------------------------");

    }

    //18. Find the zip of store name = Roseville
    @Test
    public void test018() {
        List<String> zip = response.extract().path("data.findAll{it.name=='Roseville'}.zip");
        // String servicesName = response.extract().path("data[7].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of all store are : " + zip);
        System.out.println("------------------End of Test---------------------------");

    }


    //19. Find the storeservices details of the service name = Magnolia Home Theater
    @Test
    public void test019() {
        List<HashMap<String, ?>> storeServices = response.extract().path("data[2].services.findAll{it.name== 'Magnolia Home Theater'}.storeservices");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservies are : " + storeServices);
        System.out.println("------------------End of Test---------------------------");

    }

    //20. Find the lat of all the stores
    @Test
    public void test020() {
        List<Double> latStore = response.extract().path("data.lat");
       // List<HashMap<Object, ?>> latStore = response.extract().path("data.lat");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of all store are : " + latStore);
        System.out.println("------------------End of Test---------------------------");

    }
}
