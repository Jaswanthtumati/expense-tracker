Two entity classes- User,Transaction-One user can have many transactions
 API for adding transaction --> http://localhost:8082/api/users/{userId}/transactions(POST)
  API for getting transactios ---> http://localhost:8082/api/users/2/transactions/(GET)
  For monthly summary -->http://localhost:8082/api/users/1/transactions/summary?year=2025&month=5(GET)
Upload file ---> http://localhost:8082/api/users/1/transactions/upload(POST)

TechStack--JAVA,SPRING BOOT
Database-- MySQl
Postman for testing API's
  
