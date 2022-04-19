Feature: Pokemon finder for name
  Get each pokemon by its name obtaining its basic information
  type, weight, ability and photo

  Scenario: Get pokemon for name
    Given I send name a request to the URL "/pokemon/bulbasaur"
    When the response a pokemon url will return status 200
    Then response json pokemon finder url  with body:
    """
    {"metadata":"{}","data":{"success":true,"attributes":{"name":"bulbasaur","type":"grass/poison","photo":"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/1.png","weight":69,"abilities":["overgrow","chlorophyll"]},"message":"Request pokemon  for name","status":200}}
    """