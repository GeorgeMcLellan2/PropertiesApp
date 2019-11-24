Technologies/Approaches Used:

- Data binding - to reduce boilerplate code in the Activities, Fragments & RecyclerView Adapters, keeping them modular and maintainable.
- RxJava2 - for reactive programming, allowing for API calls to be run in the background. I chose to use this because it is extremely powerful, with many useful operators accounting for almost any use case.
- Retrofit - for easily creating a HTTP API.
- MVVM - an architecture that helps to modularise components of our app, whilest being very simple to implement. 
- Picasso - for easily displaying images from URLs. There are other alternatives like Glide & Freso, but chose Picasso for familiarity.

Would be nice if had time:

- Dagger 2 - primarily for the injection of the Retrofit API interface, so that consumers did not need to worry about how to instantiate it.
- Some simple espresso & unit tests.
- Full Clean Architecture approach with UseCase objects. Would allow for greater extensibility and de-coupling - e.g. the respository could get bloated with logic which could go in the usecase, making it responsible only for serving data.
- Improve UX design - using Material Design icons, a more attractive loading view for recyclerview.
