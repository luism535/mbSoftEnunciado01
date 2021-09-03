# mbSoftEnunciado01

#Ejecutar App:
mvn clean install
mvn spring-boot:run

#EsPrioritorio
Method: GET
Url: http://localhost:8080/api/esPrioritario/{code}
UrlParam: Code - String

http://localhost:8080/api/esPrioritario/WYB-12345-9
Response OK:
{"message": "true"}

http://localhost:8080/api/esPrioritario/ZYB-12345-9
Response fail:
{"message": "false"}

http://localhost:8080/api/esPrioritario/WYB-12345-
Response Error:
{"message": "Codigo no posee un formato valido (XXX-NNNNN-N)"}

#verificar
Method: GET
Url: http://localhost:8080/api/verificar/{code}
UrlParam: Code - String

http://localhost:8080/api/verificar/DCR-88578-9
Response OK:
{"message": "true"}

http://localhost:8080/api/esPrioritario/DCR-88508-9
Response fail:
{"message": "false"}

http://localhost:8080/api/esPrioritario/DCp-88578-9
{"message": "Codigo no posee un formato valido (XXX-NNNNN-N)"}

#ordenar
Method: POST
POST http://localhost:8080/api/ordenar
Content-Type: application/json

Ejemplo Body:
{"codigos": ["ABC-83578-9", "AAB-83578-9", "ZZZ-83578-9"]}

Ejemplo Response:
["AAB-83578-9","ABC-83578-9","ZZZ-83578-9"]

#unir:
Method: POST
POST http://localhost:8080/api/unir
Content-Type: application/json

ejemplo body:
{
    "lista1" : ["ABC-83578-9", "AAB-83578-9", "ZZZ-83578-9"],
    "lista2" : ["ABC-83578-9", "AAB-83578-9", "ZZZ-83578-9"]
}

ejemplo response:
[
  "ABC-83578-9",
  "AAB-83578-9",
  "ZZZ-83578-9",
  "ABC-83578-9",
  "AAB-83578-9",
  "ZZZ-83578-9"
]

#interseccion:
Method: POST
POST http://localhost:8080/api/inter
Content-Type: application/json

ejemplo body:
{
    "lista1" : ["ABC-83578-9", "AAB-83578-9", "ZZZ-83578-9"],
    "lista2" : ["ABC-83578-0", "AAB-83578-9", "ZZZ-83578-9"]
}

ejemplo response:
[
  "AAB-83578-9",
  "ZZZ-83578-9"
]