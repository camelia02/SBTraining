//package com.bank.repo;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.core.annotation.Order;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ActiveProfiles;
//
//import com.bank.entity.AccountEntity;
//import com.bank.entity.CustomerEntity;
//import com.bank.entity.ProductEntity;
//
//// TODO: SpringBoot: Practical Bonus 1 - Unit Test for AccountRepo
//// create unit test for create, delete and get account 
//// use CustomerRepoTest as an example
//@SpringBootTest
//@ActiveProfiles("test")
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//class AccountRepoTest {
//
//    @Autowired
//    private IAccountRepo accountRepo;
//
//    @Autowired
//    private ICustomerRepo customerRepo;
//
//    @Autowired
//    private IProductRepo productRepo;
//
//    // Helper to create and save customer
//    private CustomerEntity createTestCustomer() {
//        CustomerEntity customer = new CustomerEntity();
//        customer.setIcNumber("1111-2222-5011");
//        customer.setLastname("Abu");
//        customer.setSurname("Ali");
//        customer.setDescription("Regular banking customer");
//        return customerRepo.save(customer);
//    }
//
//    // Helper to create and save product
//    private ProductEntity createTestProduct() {
//        ProductEntity product = new ProductEntity();
//        product.setProductName("Premium Savings Account");
//        product.setDescription("High-yield savings product with monthly interest payout.");
//        return productRepo.save(product);
//    }
//
//    // Helper to create and save account
//    private AccountEntity createTestAccount() {
//        CustomerEntity customer = createTestCustomer();
//        ProductEntity product = createTestProduct();
//
//        AccountEntity account = new AccountEntity();
//        account.setAccountNumber("ACC1234567890");
//        account.setBalance(1000.0);
//        account.setCreationDate(LocalDateTime.now());
//        account.setCustomerEntity(customer);
//        account.setProductEntity(product);
//
//        return accountRepo.save(account);
//    }
//
//    @Test
//    @Order(1)
//    void testCreateAccount() {
//        AccountEntity savedAccount = createTestAccount();
//
//        assertNotNull(savedAccount.getAccountID());
//        assertEquals("ACC1234567890", savedAccount.getAccountNumber());
//        assertEquals(1000.0, savedAccount.getBalance());
//        assertNotNull(savedAccount.getCustomerEntity());
//        assertNotNull(savedAccount.getProductEntity());
//    }
//
//    @Test
//    @Order(2)
//    void testFindAccountById() {
//        AccountEntity savedAccount = createTestAccount();
//
//        Optional<AccountEntity> found = accountRepo.findById(savedAccount.getAccountID());
//
//        assertTrue(found.isPresent());
//        assertEquals("ACC1234567890", found.get().getAccountNumber());
//    }
//
//    @Test
//    @Order(3)
//    void testDeleteAccount() {
//        AccountEntity savedAccount = createTestAccount();
//        Long id = savedAccount.getAccountID();
//
//        accountRepo.deleteById(id);
//
//        Optional<AccountEntity> deleted = accountRepo.findById(id);
//        assertFalse(deleted.isPresent());
//    }
//}
//
