package com.gvoltr.placeshere.data.restapi.places.parse

import com.gvoltr.placeshere.data.entity.place.Place

const val validGetPlacesJson = "{\n" +
        "  \"results\": {\n" +
        "    \"next\": \"https://places.ls.hereapi.com/places/v1/discover/explore;context=Y2F0PXNpZ2h0cy1tdXNldW1zJmZsb3ctaWQ9ZjU2MmJiYzMtNzJkYS01YWY3LThhNzAtOWUxNDQ4ZjJmMDJjXzE1ODA5OTQ3ODg0MDlfMF82MDAwJm9mZnNldD0yMCZzaXplPTIw?at=52.5159%2C13.3777&app_id=LKO34glU2MBEVbcOD5mQ&app_code=A2ta_nQ8HRYwenju5HFG5Q\",\n" +
        "    \"items\": [\n" +
        "      {\n" +
        "        \"position\": [\n" +
        "          52.51629,\n" +
        "          13.37817\n" +
        "        ],\n" +
        "        \"distance\": 54,\n" +
        "        \"title\": \"Brandenburg Gate\",\n" +
        "        \"averageRating\": 0,\n" +
        "        \"category\": {\n" +
        "          \"id\": \"landmark-attraction\",\n" +
        "          \"title\": \"Landmark/Attraction\",\n" +
        "          \"href\": \"https://places.ls.hereapi.com/places/v1/categories/places/landmark-attraction?app_id=LKO34glU2MBEVbcOD5mQ&app_code=A2ta_nQ8HRYwenju5HFG5Q\",\n" +
        "          \"type\": \"urn:nlp-types:category\",\n" +
        "          \"system\": \"places\"\n" +
        "        },\n" +
        "        \"icon\": \"https://download.vcdn.data.here.com/p/d/places2/icons/categories/38.icon\",\n" +
        "        \"vicinity\": \"Pariser Platz<br/>Mitte, 10117 Berlin\",\n" +
        "        \"having\": [\n" +
        "          \n" +
        "        ],\n" +
        "        \"type\": \"urn:nlp-types:place\",\n" +
        "        \"href\": \"https://places.ls.hereapi.com/places/v1/places/276u33db-8ee2e0de906e459cbade0593986debe9;context=Zmxvdy1pZD1mNTYyYmJjMy03MmRhLTVhZjctOGE3MC05ZTE0NDhmMmYwMmNfMTU4MDk5NDc4ODQwOV8wXzYwMDAmcmFuaz0w?app_id=LKO34glU2MBEVbcOD5mQ&app_code=A2ta_nQ8HRYwenju5HFG5Q\",\n" +
        "        \"id\": \"276u33db-8ee2e0de906e459cbade0593986debe9\",\n" +
        "        \"alternativeNames\": [\n" +
        "          {\n" +
        "            \"name\": \"Brandenburger Tor\",\n" +
        "            \"language\": \"de\"\n" +
        "          }\n" +
        "        ]\n" +
        "      },\n" +
        "      {\n" +
        "        \"position\": [\n" +
        "          52.51373,\n" +
        "          13.37976\n" +
        "        ],\n" +
        "        \"distance\": 279,\n" +
        "        \"title\": \"Holocaust Memorial\",\n" +
        "        \"averageRating\": 0,\n" +
        "        \"category\": {\n" +
        "          \"id\": \"museum\",\n" +
        "          \"title\": \"Museum\",\n" +
        "          \"href\": \"https://places.ls.hereapi.com/places/v1/categories/places/museum?app_id=LKO34glU2MBEVbcOD5mQ&app_code=A2ta_nQ8HRYwenju5HFG5Q\",\n" +
        "          \"type\": \"urn:nlp-types:category\",\n" +
        "          \"system\": \"places\"\n" +
        "        },\n" +
        "        \"icon\": \"https://download.vcdn.data.here.com/p/d/places2/icons/categories/10.icon\",\n" +
        "        \"vicinity\": \"Cora-Berliner-Straße 1<br/>Mitte, 10117 Berlin\",\n" +
        "        \"having\": [\n" +
        "          \n" +
        "        ],\n" +
        "        \"type\": \"urn:nlp-types:place\",\n" +
        "        \"href\": \"https://places.ls.hereapi.com/places/v1/places/276u33de-df7d57fd38494a93b2018fe549a0fd75;context=Zmxvdy1pZD1mNTYyYmJjMy03MmRhLTVhZjctOGE3MC05ZTE0NDhmMmYwMmNfMTU4MDk5NDc4ODQwOV8wXzYwMDAmcmFuaz0x?app_id=LKO34glU2MBEVbcOD5mQ&app_code=A2ta_nQ8HRYwenju5HFG5Q\",\n" +
        "        \"id\": \"276u33de-df7d57fd38494a93b2018fe549a0fd75\",\n" +
        "        \"openingHours\": {\n" +
        "          \"text\": \"Tue-Sun: 10:00 - 20:00\",\n" +
        "          \"label\": \"Opening hours\",\n" +
        "          \"isOpen\": true,\n" +
        "          \"structured\": [\n" +
        "            {\n" +
        "              \"start\": \"T100000\",\n" +
        "              \"duration\": \"PT10H00M\",\n" +
        "              \"recurrence\": \"FREQ:DAILY;BYDAY:TU,WE,TH,FR,SA,SU\"\n" +
        "            }\n" +
        "          ]\n" +
        "        },\n" +
        "        \"alternativeNames\": [\n" +
        "          {\n" +
        "            \"name\": \"Memorial to the Murdered European Jews\",\n" +
        "            \"language\": \"en\"\n" +
        "          },\n" +
        "          {\n" +
        "            \"name\": \"Memorial to the Murdered Jews of Europe\",\n" +
        "            \"language\": \"en\"\n" +
        "          },\n" +
        "          {\n" +
        "            \"name\": \"Denkmal für die ermordeten Juden Europas\",\n" +
        "            \"language\": \"de\"\n" +
        "          }\n" +
        "        ]\n" +
        "      }\n" +
        "    ]\n" +
        "  }\n" +
        "}"

val validParsedPlaces = listOf(
    Place(
        latitude = "52.51629",
        longitude = "13.37817",
        title = "Brandenburg Gate",
        vicinity = "Pariser Platz<br/>Mitte, 10117 Berlin",
        icon = "https://download.vcdn.data.here.com/p/d/places2/icons/categories/38.icon",
        detailsLink = "https://places.ls.hereapi.com/places/v1/places/276u33db-8ee2e0de906e459cbade0593986debe9;context=Zmxvdy1pZD1mNTYyYmJjMy03MmRhLTVhZjctOGE3MC05ZTE0NDhmMmYwMmNfMTU4MDk5NDc4ODQwOV8wXzYwMDAmcmFuaz0w?app_id=LKO34glU2MBEVbcOD5mQ&app_code=A2ta_nQ8HRYwenju5HFG5Q",
        categoryId = "landmark-attraction",
        categoryTitle = "Landmark/Attraction"
    ),
    Place(
        latitude = "52.51373",
        longitude = "13.37976",
        title = "Holocaust Memorial",
        vicinity = "Cora-Berliner-Straße 1<br/>Mitte, 10117 Berlin",
        icon = "https://download.vcdn.data.here.com/p/d/places2/icons/categories/10.icon",
        detailsLink = "https://places.ls.hereapi.com/places/v1/places/276u33de-df7d57fd38494a93b2018fe549a0fd75;context=Zmxvdy1pZD1mNTYyYmJjMy03MmRhLTVhZjctOGE3MC05ZTE0NDhmMmYwMmNfMTU4MDk5NDc4ODQwOV8wXzYwMDAmcmFuaz0x?app_id=LKO34glU2MBEVbcOD5mQ&app_code=A2ta_nQ8HRYwenju5HFG5Q",
        categoryId = "museum",
        categoryTitle = "Museum"
    )
)
