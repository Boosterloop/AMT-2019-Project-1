import json
import requests

api_url_base = 'https://restcountries.eu/rest/v2/'

headers = {'Content-Type': 'application/json'}

output_file = "../db-data/2_countries.csv"

# Get data from API
def get_countries():
    api_url = '{0}all'.format(api_url_base)

    response = requests.get(api_url, headers=headers)

    if response.status_code == 200:
        return json.loads(response.content.decode('utf-8'))
    else:
        return None


countries = get_countries()

if countries is not None:
    sql_query = "countryCode, name\n"

    for country in countries:
        name = country['name'].translate(str.maketrans({"'": "\\'"}))
        sql_query += "{0}, {1}\n".format(country['alpha2Code'], name)

    print(sql_query)

    # Open file in write mode (and create it if necessary)
    with open(output_file, "w+") as writer:
        writer.write(sql_query)

else:
    print('[!] Request Failed')

