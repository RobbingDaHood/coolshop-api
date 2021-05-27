# coolshop-api

Find swagger-ui at: http://localhost:8080/swagger-ui
Raw swagger is at: http://localhost:8080/v2/api-docs 


## Structurizer
Non public structurizer workspace: https://structurizr.com/workspace/67343

### Context
![Alt text](structurizer/SystemContext.png?raw=true "Title")
### Containers
![Alt text](structurizer/containerContext.png?raw=true "Title")
### Components Customer
![Alt text](structurizer/customerContainer.png?raw=true "Title")
### Components Order
![Alt text](structurizer/OrderContainer.png?raw=true "Title")

### Code
    workspace {
        model {
            customer = person "Customer" "Customer of Coolshop"
            
            softwareSystem = softwareSystem "Coolshop system" {
                customerContainer = container "Customer Application" {
                    customerExposure = component "Customer Exposure"
                    customerDomain = component "Customer Domain"
                    customerPersistance = component "Customer Persistance"
                    
                    customerExposure -> customerDomain "Reads and writes Customers"
                    customerPersistance -> customerDomain "Listen to reads and write for Customers"
                }
                orderContainer = container "Order Application" {
                    orderExposure = component "Order Exposure"
                    orderDomain = component "Order Domain"
                    orderPersistance = component "Order Persistance"
                    customerConnector = component "Connects to Customer container"
                    
                    orderExposure -> orderDomain "Reads and writes Orders"
                    orderPersistance -> orderDomain "Listen to reads and write for Orders"
                    customerConnector -> orderDomain "Listen to reads for Customers"
                }
                customerDatabase = container "Customer Database"
                orderDatabase = container "Order Database"
             }
    
            customer -> customerExposure "Read and write Customers"
            customer -> orderExposure "Read and write Orders"
            customerConnector -> customerExposure "Fetches User info"
            customerPersistance -> customerDatabase "Reads from and writes to"
            orderPersistance -> orderDatabase "Reads from and writes to"
        }
        
        views {
            systemContext softwareSystem "SystemContext" {
                include *
                autoLayout
            }
            
            container softwareSystem "containerContext" {
                include *
                autoLayout
            }
            
            component customerContainer "customerContainer" {
                include *
                autoLayout
            }
            
            component orderContainer "orderContainer" {
                include *
                autoLayout
            }
    
            styles {
                element "Software System" {
                    background #1168bd
                    color #ffffff
                }
                element "Person" {
                    shape person
                    background #08427b
                    color #ffffff
                }
            }
        }
    }

