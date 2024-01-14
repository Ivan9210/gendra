# City Suggestions Service

Welcome to the City Suggestions Service! This service provides suggestions for cities based on a search query and optional location coordinates.

## How to Consume the Service

To consume the service, you can use the `curl` command in your terminal. Here's an example:

curl -X GET "https://gendra-411215.ue.r.appspot.com/api/gendra/suggestions?q=Londo&latitude=43.70011&longitude=-79.4163"

Replace the `q`, `latitude`, and `longitude` parameters with your desired values.

## City Suggestions API

### Endpoint

- **GET /api/gendra/suggestions**

### Parameters

- **q** (required): The search query parameter.
- **latitude** (optional): The latitude parameter for location-based suggestions.
- **longitude** (optional): The longitude parameter for location-based suggestions.

Replace the `q`, `latitude`, and `longitude` parameters with your desired values.

### Example

GET https://gendra-411215.ue.r.appspot.com/api/gendra/suggestions?q=Londo&latitude=43.70011&longitude=-79.4163

Replace the `q`, `latitude`, and `longitude` parameters with your desired values.

If you encounter any issues or have questions, please contact the service administrators.