# 🛒 Mercado Libre

A mobile application that allows users to search and view product details using Mercado Libre's mock APIs. Built with Jetpack Compose and modern Android architecture.

## Observations
**Notice: Temporary Use of Mocked Data Due to API Authentication Issue**

During development, I attempted to integrate with the MercadoLibre API (developers.mercadolibre.com) for live data retrieval. However, despite successfully generating an access token, the API consistently returned a 403 Forbidden error:
```
{  
  "message": "forbidden",  
  "error": "forbidden",  
  "status": 403,  
  "cause": []  
}  
```

## Screenshots



| Home | Search | Results from search | Product Details | Image Zoom |
|--------|--------|--------|--------|--------|
| ![image](https://github.com/user-attachments/assets/12dd387b-1b4c-494a-9dac-0fcc4aa4825c) | ![image](https://github.com/user-attachments/assets/6904a0fb-2c1b-4eed-8fc3-1349e3e514aa) | ![image](https://github.com/user-attachments/assets/d569a7ad-0a02-48d9-af68-293faa512370) | ![image](https://github.com/user-attachments/assets/69d4550a-d291-4192-baae-67fb274f7a76) | ![image](https://github.com/user-attachments/assets/dcf7e28a-1731-4d98-a2c6-f58efe51a95b) |


## Features
- **Search Products**: Enter a search term to find relevant items.
- **Product List**: Display search results with thumbnails, titles, and prices.
- **Product Details**: Show comprehensive product info including description, condition, category, and high-resolution images.
- **Rotation Support**: Maintain UI state during screen rotation.
- **Error Handling**: Graceful error messages for API failures and empty states.
- **Local JSON Simulation**: Mock API responses for offline development.

## Technologies
- **Jetpack Compose** - Modern declarative UI toolkit
- **Voyager** - Navigation library for Compose
- **Koin** - Dependency injection
- **Coil** - Image loading
- **Zoomable** - Image zoom capabilities
- **Lyricist** - Multi-language support
- **Gson** - JSON parsing
- **JUnit, MockK, Turbine** - Testing framework

## Structure
- **MVVM** - Clear separation between View (UI components), ViewModel (business logic and state management), and Model (data layer).
- **Clean Architecture** - Layered structure divided into:
  - **Data**: Handles data sources (local, remote) and implementation details.
  - **Domain**: Contains business rules, entities, and interfaces (abstractions).
  - **Presentation**: Manages UI, state, and user interactions.
- **SOLID** - Five principles to write maintainable, scalable, and testable code:
  - **Single Responsibility (SRP)**: A class should have one reason to change.
  - **Open/Closed (OCP)**: Classes should be open for extension (via inheritance/composition) but closed for modification.
  - **Liskov Substitution (LSP)**: Subtypes must be replaceable with their base types without breaking behavior.
  - **Interface Segregation (ISP)**: Avoid forcing clients to depend on unused methods. Split large interfaces.
  - **Dependency Inversion (DIP)**: Depend on abstractions (interfaces), not concrete implementations.

## Installation
1. Clone the repository:
```
git clone https://github.com/yourusername/mercado-libre-app.git

Open in Android Studio (i'm using Android Studio Narwhal | 2025.1.1 Nightly 2025-05-12) with agp = "8.10.0"

Sync Gradle dependencies

Build & Run on emulator/device (API 26+)
```
2. Usage:
```
Home Screen:
  - Has a clickable header to going to searchScreen
  - Has a simulated loading

Search Screen:
  - Input to search products

Results Screen:
  - View results of your search

Detail Screen:
  - View more details of a product

Image Details Screen:
  - View zoomable images
  
```
