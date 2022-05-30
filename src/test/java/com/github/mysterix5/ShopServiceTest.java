package com.github.mysterix5;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

class ShopServiceTest {
    Product myTestProduct;
    List<Product> products;
    ProductRepo productRepo;
    OrderRepo orderRepo;
    ShopService shopService;
    @BeforeEach
    public void setup(){
//        System.out.println("Setup products, productRepo, orderRepo, shopService");
        // create product repo
        products = new ArrayList<>();
        myTestProduct = new DigitalProduct("Towel");
        Product pen = new PhysicalProduct("Pen");
        products.add(myTestProduct);
        products.add(pen);
        productRepo = new ProductRepo();
        productRepo.fill(products);
        // create order repo
        orderRepo = new OrderRepo();
        // create shop service
        shopService = new ShopService(productRepo, orderRepo);
    }


    @Nested
    class orders{
        @Test
        @DisplayName("Add order to shopService")
        void addOrderWithValidProductsShouldPass () {
        // add order
        String orderId = shopService.addOrder(List.of(myTestProduct.getId()));

        // test if product lists match
        assertThat(shopService.getOrder(orderId).getProducts())
                .as("creating an order and adding it")
                .containsExactly(products.get(0));
    }
        @Test
        void addOrderWithInvalidProductsShouldThrow () {
            assertThatThrownBy(()->shopService.addOrder(List.of("notValidId")))
                    .isExactlyInstanceOf(RuntimeException.class)
                    .hasMessage("Adding order failed")
                    .getCause().isExactlyInstanceOf(NoSuchElementException.class)
                    .hasMessage("Product with id 'notValidId' is not available!");
    }
        @Test
        void addOrderWithNoProductsShouldThrow () {
            assertThatThrownBy(()->shopService.addOrder(new ArrayList<>()))
                    .isExactlyInstanceOf(RuntimeException.class)
                    .hasMessage("Adding order failed")
                    .getCause().isExactlyInstanceOf(RuntimeException.class)
                    .hasMessage("It is not possible to add an order with zero products!");
    }

        @Test
        void getOrderValidShouldPass () {
        // add order
        String orderId = shopService.addOrder(List.of(myTestProduct.getId()));

        // get order
        Order actual = shopService.getOrder(orderId);

        assertThat(shopService.getOrder(orderId).getProducts()).containsExactly(products.get(0));
    }
        @Test
        void getOrderWrongIdShouldThrow () {
        // add order
        String orderId = shopService.addOrder(List.of(myTestProduct.getId()));

        assertThatThrownBy(()->shopService.getOrder("notValidId"))
                .isExactlyInstanceOf(RuntimeException.class)
                .hasMessage("Order with id 'notValidId' is not available!")
                .hasNoCause();
    }

        @Test
        void listOrdersContainingSome () {
        // add order
        String orderId1 = shopService.addOrder(List.of(myTestProduct.getId()));
        String orderId2 = shopService.addOrder(List.of(myTestProduct.getId(), products.get(1).getId()));

        assertThat(shopService.listOrders())
                .extracting(Order::getProducts)
                .containsExactlyInAnyOrder(products, List.of(products.get(0)));
        }

        @Test
        void listOrdersEmpty () {
            assertThat(shopService.listOrders())
                .extracting(Order::getProducts)
                .isEmpty();
        }

    }
    @Nested
    class products {
        @Test
        void getProductValid() {
            // set expected and actual product
            Product expected = myTestProduct;
            Product actual = shopService.getProduct(expected.getId());

            // test if original product matches getProduct(id) from shopService
            assertThat(actual).isEqualTo(expected);
        }

        @Test
        void getProductByName() {
            var expected = myTestProduct;
            var actual = shopService.getProductByName(expected.getName());
            assertThat(actual).isEqualTo(expected);
        }
        @Test
        void getProductByNameNonExistent() {
            assertThatThrownBy(()->shopService.getProductByName("NonExistentProductName"))
                    .isExactlyInstanceOf(NoSuchElementException.class)
                    .hasNoCause();
        }

        @Test
        void getProductInvalidId() {
            assertThatThrownBy(()->shopService.getProduct("notValidId"))
                    .isExactlyInstanceOf(RuntimeException.class)
                    .hasMessage("Product with id 'notValidId' is not available!")
                    .hasNoCause();
        }

        @Test
        void listProductsValid() {
            // set expected and actual product list
            List<Product> actual = shopService.listProducts();

            // test if original products match listProducts() from shopService
            assertThat(actual).containsExactlyInAnyOrderElementsOf(products);
        }
    }

}
