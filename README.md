# Getting Started with Spring Elastic Search integration

### Application Documentation

* Start Elastic Search
* Spring Boot - Elastic Search integration application

## below are steps to add records and search by attributes

* Add a person

POST http://localhost:8080/spring_es/v1/

Content-Type : application/json

request body : 

    {
        "id": "1",
        "first_name": "sunil",
        "last_name": "kumar",
        "addresses": [
            {
                "address_id": "addr1",
                "address_line1": "33 south 6th steet",
                "address_line2": "6th steet",
                "city": "minneapolis",
                "state_code": "MN",
                "zip_code": "55402"
            }
        ],
        "internet_ids": [
            "sunil.kumar@target.com",
            "sunil@yahoo"
        ]
    }

response : person json once successfully saved


* GET Person by id

GET http://localhost:8080/spring_es/v1/1
response : 

    {
        "id": "1",
        "first_name": "sunil",
        "last_name": "kumar",
        "addresses": [
            {
                "address_id": "addr1",
                "address_line1": "33 south 6th steet",
                "address_line2": "6th steet",
                "city": "minneapolis",
                "state_code": "MN",
                "zip_code": "55402"
            }
        ],
        "internet_ids": [
            "sunil.kumar@target.com",
            "sunil@yahoo"
        ]
    }

* GET Person by first and last name

GET http://localhost:8080/spring_es/v1/search_by_firstname_lastname?first_name=sunil&last_name=kumar

response

    [
        {
            "id": "1",
            "first_name": "sunil",
            "last_name": "kumar",
            "addresses": [
                {
                    "address_id": "addr1",
                    "address_line1": "33 south 6th steet",
                    "address_line2": "6th steet",
                    "city": "minneapolis",
                    "state_code": "MN",
                    "zip_code": "55402"
                }
            ],
            "internet_ids": [
                "sunil.kumar@target.com",
                "sunil@yahoo"
            ]
        }
    ]

* fetch list of persons by multiple search attributes that need to match

POST  http://localhost:8080/spring_es/v1/search

Content-Type : application/json

request body :

    {   
        "first_name": "sunil",
        "last_name": "kumar",
        "internet_ids": "sunil@yahoo.com",
        "addresses.city": "minneapolis",
        "addresses.zip_code": "55402",
        "addresses.state_code": "MN",
        "addresses.address_id": "addr1",
        "addresses.address_line1": "33 south 6th steet"
    }

response 

    [
        {
            "id": "1",
            "first_name": "sunil",
            "last_name": "kumar",
            "addresses": [
                {
                    "address_id": "addr1",
                    "address_line1": "33 south 6th steet",
                    "address_line2": "6th steet",
                    "city": "minneapolis",
                    "state_code": "MN",
                    "zip_code": "55402"
                }
            ],
            "internet_ids": [
                "sunil.kumar@target.com",
                "sunil@yahoo"
            ]
        }
    ]

* fetch list of Persons by any matching attribute

POST http://localhost:8080/spring_es/v1/search_any


Content-Type : application/json

request body :

    {
        "first_name": "john",
        "last_name": "kumar"
    }
    
response : (assumption that  : John Doe (id = 2)  was added) will result in  below matching records 

    [
        {
            "id": "1",
            "first_name": "sunil",
            "last_name": "kumar",
            "addresses": [
                {
                    "address_id": "addr1",
                    "address_line1": "33 south 6th steet",
                    "address_line2": "6th steet",
                    "city": "minneapolis",
                    "state_code": "MN",
                    "zip_code": "55402"
                }
            ],
            "internet_ids": [
                "sunil.kumar@target.com",
                "sunil@yahoo"
            ]
        },
        {
            "id": "2",
            "first_name": "John",
            "last_name": "Doe",
            "addresses": [
                {
                    "address_id": "addr1",
                    "address_line1": "12000",
                    "address_line2": null,
                    "city": "minnetonka",
                    "state_code": "MN",
                    "zip_code": "55305"
                }
            ],
            "internet_ids": [
                "John.Doe@johndoe.com",
                "me@yahoo"
            ]
        }
    ]

