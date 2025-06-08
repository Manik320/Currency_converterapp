# Currency Converter Spring Boot Application

A modern, responsive currency converter web application built with Spring Boot and Thymeleaf.

## Features

- **Real-time Exchange Rates**: Fetches live exchange rates from external APIs
- **Responsive Design**: Modern UI with Bootstrap and custom CSS
- **RESTful API**: Provides REST endpoints for integration
- **Multiple Currencies**: Supports 20+ major world currencies
- **Fallback System**: Uses fallback rates when API is unavailable
- **Input Validation**: Comprehensive form validation
- **Error Handling**: Graceful error handling and user feedback

## Technologies Used

- **Backend**: Spring Boot 3.1.0, Spring Web, Spring WebFlux
- **Frontend**: Thymeleaf, Bootstrap 5, Font Awesome
- **Build Tool**: Maven
- **Java Version**: 17

## Supported Currencies

- USD (US Dollar)
- EUR (Euro)
- GBP (British Pound)
- JPY (Japanese Yen)
- AUD (Australian Dollar)
- CAD (Canadian Dollar)
- CHF (Swiss Franc)
- CNY (Chinese Yuan)
- And 12 more...

## API Endpoints

### Get Supported Currencies
```
GET /api/currencies
```

### Convert Currency (GET)
```
GET /api/convert?amount=100&from=USD&to=EUR
```

### Convert Currency (POST)
```
POST /api/convert
Content-Type: application/json

{
  "amount": 100.0,
  "fromCurrency": "USD",
  "toCurrency": "EUR"
}
```

## Setup and Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd currency-converter
   ```

2. **Build the application**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   Open your browser and navigate to `http://localhost:7878`

## Configuration

The application can be configured through `application.properties`:

```properties
# Server configuration
server.port=7878

# Exchange rate API
exchange.api.url=https://api.exchangerate-api.com/v4/latest

# Thymeleaf configuration
spring.thymeleaf.cache=false
```

## Exchange Rate API

The application uses the Exchange Rate API (exchangerate-api.com) for live rates. If the API is unavailable, it falls back to predefined rates for demonstration purposes.

To use a different API, modify the `ExchangeRateService` class and update the API URL in the configuration.

## Project Structure

```
src/
├── main/
│   ├── java/com/example/currencyconverter/
│   │   ├── controller/          # Web and REST controllers
│   │   ├── model/              # Data models
│   │   ├── service/            # Business logic
│   │   └── CurrencyConverterApplication.java
│   └── resources/
│       ├── templates/          # Thymeleaf templates
│       └── application.properties
└── test/                       # Unit tests
```

## Testing

Run the tests using Maven:
```bash
mvn test
```

## Features in Detail

### Web Interface
- Clean, modern design with gradient backgrounds
- Responsive layout that works on all devices
- Real-time form validation
- Animated results display
- Currency swap functionality

### API Interface
- RESTful endpoints for programmatic access
- JSON request/response format
- Proper HTTP status codes
- Input validation

### Error Handling
- Graceful fallback when external API is unavailable
- User-friendly error messages
- Comprehensive input validation
- Custom error pages

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request
