The Dog Blog
==================

This is a starter project using Cloud Firestore in Datastore Mode, Memcached, and JSP.

URL: https://paco-project-268923.appspot.com/

## Setup

    gcloud init
    gcloud auth application-default login

## Maven
### Running locally

    mvn appengine:run

### Deploying

    mvn appengine:deploy

## Testing

    mvn verify

## Documentation Used

Appengine Getting Started [https://cloud.google.com/appengine/docs/standard/java/quickstart]
Using Cloud Firestore in Datastore Mode [https://cloud.google.com/appengine/docs/standard/java/using-cloud-datastore]
Using Memcache [https://cloud.google.com/appengine/docs/standard/java/memcache/using]