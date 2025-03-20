# Fetch Hiring App

## Overview
The app fetches a list of items from [Fetch Hiring API](https://fetch-hiring.s3.amazonaws.com/hiring.json) and displays them as a list. The data is:

- **Grouped by** `listId`
- **Sorted by** `listId` and then by `name`
- **Not stored in a database**; it is fetched every time the app is opened.
- If there is **no internet connection**, a placeholder is shown.

## Tech Stack
The app is built using the following technologies:

- **Jetpack Compose** – For UI development.
- **Flow** – For reactive data handling.
- **ViewModel** – For state management.
- **Retrofit** – For making network requests.
- **Hilt** – For dependency injection.

## Screenshots 

![image](https://github.com/user-attachments/assets/9f20605b-f31b-43bc-b0b6-482c0f355f87)

![image](https://github.com/user-attachments/assets/ad336edd-1e6c-4544-8135-1d8cf6f1a360)


