Feature: Get all Pokemon and create pagination items

  Scenario: Get all pokemon
    Given I send name a request to the URL "/pokemon?offset=120&limit=20"
    When the response a pokemon url will return status 200
    Then response json pokemon finder url  with body:
    """
    {"metadata":"{}","data":{"success":true,"attributes":{"count":1126,"next":"?offset=140&limit=20","previous":"?offset=100&limit=20","results":[{"name":"starmie","url":"/pokemon/starmie"},{"name":"mr-mime","url":"/pokemon/mr-mime"},{"name":"scyther","url":"/pokemon/scyther"},{"name":"jynx","url":"/pokemon/jynx"},{"name":"electabuzz","url":"/pokemon/electabuzz"},{"name":"magmar","url":"/pokemon/magmar"},{"name":"pinsir","url":"/pokemon/pinsir"},{"name":"tauros","url":"/pokemon/tauros"},{"name":"magikarp","url":"/pokemon/magikarp"},{"name":"gyarados","url":"/pokemon/gyarados"},{"name":"lapras","url":"/pokemon/lapras"},{"name":"ditto","url":"/pokemon/ditto"},{"name":"eevee","url":"/pokemon/eevee"},{"name":"vaporeon","url":"/pokemon/vaporeon"},{"name":"jolteon","url":"/pokemon/jolteon"},{"name":"flareon","url":"/pokemon/flareon"},{"name":"porygon","url":"/pokemon/porygon"},{"name":"omanyte","url":"/pokemon/omanyte"},{"name":"omastar","url":"/pokemon/omastar"},{"name":"kabuto","url":"/pokemon/kabuto"}]},"message":"Request all pokemon for pages","status":200}}
    """