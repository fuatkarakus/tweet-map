# Useful Queries

Mongodb has some limitation;
https://docs.mongodb.com/manual/reference/limits/#Queries-cannot-use-both-text-and-Geospatial-Indexes

geo location and text search 
```
db.status.find(
    {
        $and: [
            {
                "geoLocation":
                    {
                        $geoWithin:
                            {
                                $center: [
                                    [41.01224, 28.448674], 2]

                            }
                    }
            },
            {
                $text: {
                        $search: "corona"
                }
            }

        ]
    }, 
    { _id: 1, text: 1, "place.fullName": 1, "user.screenName": 1 })
```
