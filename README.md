# Stock Analysis Bot - Backend

A Spring Boot backend application for stock market analysis. The application fetches stock market data from Finnhub, calculates technical indicators, and provides buy/sell/hold signals through REST APIs.

## Features

* Stock analysis by symbol
* RSI (Relative Strength Index) calculation
* MACD (Moving Average Convergence Divergence) calculation
* EMA (Exponential Moving Average) support
* Analysis history storage
* Portfolio management APIs
* Watchlist management APIs
* PostgreSQL database integration
* Finnhub API integration

## Technologies

* Java 21
* Spring Boot
* Spring Data JPA
* PostgreSQL
* Maven
* TA4J
* Finnhub API

## Project Structure

```text
src/main/java/com/aynur/stockbot
│
├── controller
├── service
├── repository
├── model
├── mapper
└── config
```

## Setup

### Clone Repository

```bash
git clone https://github.com/aynurdenikayeva/stock-analysis-bot.git
cd stock-analysis-bot
```

### Configure Finnhub API Key

Create or update:

```yaml
src/main/resources/application.yml
```

```yaml
finnhub:
  api-key: YOUR_API_KEY
```

### Configure PostgreSQL

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/stockdb
    username: postgres
    password: your_password
```

### Run Application

```bash
mvn spring-boot:run
```

Application starts on:

```text
http://localhost:8080
```

## API Examples

Analyze Stock:

```http
GET /api/stocks/analyze/AAPL
```

Analysis History:

```http
GET /api/stocks/history/AAPL
```

Portfolio:

```http
GET /api/portfolio
```

Watchlist:

```http
GET /api/watchlist
```

## Future Improvements

* Authentication & Authorization
* Real-time market updates
* Advanced technical indicators
* News sentiment analysis
* AI-powered stock predictions
