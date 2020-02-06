package com.gvoltr.placeshere.data.restapi.address.parse

import com.gvoltr.placeshere.data.entity.address.Address

val validGetAddressResponse = "{\n" +
        "  \"Response\": {\n" +
        "    \"MetaInfo\": {\n" +
        "      \"Timestamp\": \"2020-02-06T10:41:37.071+0000\",\n" +
        "      \"NextPageInformation\": \"2\"\n" +
        "    },\n" +
        "    \"View\": [\n" +
        "      {\n" +
        "        \"_type\": \"SearchResultsViewType\",\n" +
        "        \"ViewId\": 0,\n" +
        "        \"Result\": [\n" +
        "          {\n" +
        "            \"Relevance\": 1,\n" +
        "            \"Distance\": 13.6,\n" +
        "            \"MatchLevel\": \"houseNumber\",\n" +
        "            \"MatchQuality\": {\n" +
        "              \"Country\": 1,\n" +
        "              \"State\": 1,\n" +
        "              \"County\": 1,\n" +
        "              \"City\": 1,\n" +
        "              \"District\": 1,\n" +
        "              \"Street\": [\n" +
        "                1\n" +
        "              ],\n" +
        "              \"HouseNumber\": 1,\n" +
        "              \"PostalCode\": 1\n" +
        "            },\n" +
        "            \"MatchType\": \"pointAddress\",\n" +
        "            \"Location\": {\n" +
        "              \"LocationId\": \"NT_Opil2LPZVRLZjlWNLJQuWB_0ITN\",\n" +
        "              \"LocationType\": \"address\",\n" +
        "              \"DisplayPosition\": {\n" +
        "                \"Latitude\": 41.88432,\n" +
        "                \"Longitude\": -87.63877\n" +
        "              },\n" +
        "              \"NavigationPosition\": [\n" +
        "                {\n" +
        "                  \"Latitude\": 41.88449,\n" +
        "                  \"Longitude\": -87.63877\n" +
        "                }\n" +
        "              ],\n" +
        "              \"MapView\": {\n" +
        "                \"TopLeft\": {\n" +
        "                  \"Latitude\": 41.8854442,\n" +
        "                  \"Longitude\": -87.64028\n" +
        "                },\n" +
        "                \"BottomRight\": {\n" +
        "                  \"Latitude\": 41.8831958,\n" +
        "                  \"Longitude\": -87.63726\n" +
        "                }\n" +
        "              },\n" +
        "              \"Address\": {\n" +
        "                \"Label\": \"425 W Randolph St, Chicago, IL 60606, United States\",\n" +
        "                \"Country\": \"USA\",\n" +
        "                \"State\": \"IL\",\n" +
        "                \"County\": \"Cook\",\n" +
        "                \"City\": \"Chicago\",\n" +
        "                \"District\": \"West Loop\",\n" +
        "                \"Street\": \"W Randolph St\",\n" +
        "                \"HouseNumber\": \"425\",\n" +
        "                \"PostalCode\": \"60606\",\n" +
        "                \"AdditionalData\": [\n" +
        "                  {\n" +
        "                    \"value\": \"United States\",\n" +
        "                    \"key\": \"CountryName\"\n" +
        "                  },\n" +
        "                  {\n" +
        "                    \"value\": \"Illinois\",\n" +
        "                    \"key\": \"StateName\"\n" +
        "                  },\n" +
        "                  {\n" +
        "                    \"value\": \"Cook\",\n" +
        "                    \"key\": \"CountyName\"\n" +
        "                  },\n" +
        "                  {\n" +
        "                    \"value\": \"N\",\n" +
        "                    \"key\": \"PostalCodeType\"\n" +
        "                  }\n" +
        "                ]\n" +
        "              },\n" +
        "              \"MapReference\": {\n" +
        "                \"ReferenceId\": \"776372180\",\n" +
        "                \"MapId\": \"NAAM19147\",\n" +
        "                \"MapVersion\": \"Q1/2019\",\n" +
        "                \"MapReleaseDate\": \"2020-01-24\",\n" +
        "                \"Spot\": 0.52,\n" +
        "                \"SideOfStreet\": \"right\",\n" +
        "                \"CountryId\": \"21000001\",\n" +
        "                \"StateId\": \"21002247\",\n" +
        "                \"CountyId\": \"21002623\",\n" +
        "                \"CityId\": \"21002647\",\n" +
        "                \"BuildingId\": \"9000000000002726912\",\n" +
        "                \"AddressId\": \"79186508\",\n" +
        "                \"RoadLinkId\": \"170008450\"\n" +
        "              }\n" +
        "            }\n" +
        "          }\n" +
        "        ]\n" +
        "      }\n" +
        "    ]\n" +
        "  }\n" +
        "}"

val validParsedAddress = Address("425 W Randolph St, Chicago, IL 60606, United States")