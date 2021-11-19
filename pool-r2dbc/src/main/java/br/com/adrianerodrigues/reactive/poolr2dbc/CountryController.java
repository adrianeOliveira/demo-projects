package br.com.adrianerodrigues.reactive.poolr2dbc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/countries")
public class CountryController {

  private final CountryRepository countryRepository;

  @GetMapping
  public Flux<Country> getAllCountries() {
    return countryRepository.findAll()
        .doOnComplete(() -> log.info("Search for all countries finished, size"));
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<Country> saveNewCountry(@RequestBody NewCountryDto country) {
    return countryRepository.save(Country.builder().name(country.getName()).build())
        .doOnSuccess(countryResult ->  log.info("Contry saved, {}", countryResult));
  }

  @GetMapping("/{countryId}")
  public Mono<Country> findById(@PathVariable Integer countryId) {
    return countryRepository.findById(countryId)
        .switchIfEmpty(Mono.error(
            new WebClientResponseException(HttpStatus.NOT_FOUND.value(), "Resource not found", null, null, null))
        )
        .doOnSuccess(result -> log.info("Country found, {}", result));
  }

  @PutMapping("/{countryId}")
  public Mono<Country> updateCountry(@PathVariable Integer countryId, @RequestBody UpdateCountryDto country) {
    return countryRepository.findById(countryId)
        .switchIfEmpty(Mono.error(
            new WebClientResponseException(HttpStatus.NOT_FOUND.value(), "Resource not found", null, null, null))
        ).flatMap(countryFromDB -> {
          countryFromDB.setName(country.getName());
          return countryRepository.save(countryFromDB);
        })
        .doOnSuccess(result -> log.info("Country updated, {}", country));
  }

}
