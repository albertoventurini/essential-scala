case class Film(
                 name: String,
                 yearOfRelease: Int,
                 imdbRating: Double)

case class Director(
                     firstName: String,
                     lastName: String,
                     yearOfBirth: Int,
                     films: Seq[Film])

val memento = new Film("Memento", 2000, 8.5)
val darkKnight = new Film("Dark Knight", 2008, 9.0)
val inception = new Film("Inception", 2010, 8.8)
val highPlainsDrifter = new Film("High Plains Drifter", 1973, 7.7)
val outlawJoseyWales = new Film("The Outlaw Josey Wales", 1976, 7.9)
val unforgiven = new Film("Unforgiven", 1992, 8.3)
val granTorino = new Film("Gran Torino", 2008, 8.2)
val invictus = new Film("Invictus", 2009, 7.4)
val predator = new Film("Predator", 1987, 7.9)
val dieHard = new Film("Die Hard", 1988, 8.3)
val huntForRedOctober = new Film("The Hunt for Red October", 1990, 7.6)
val thomasCrownAffair = new Film("The Thomas Crown Affair", 1999, 6.8)
val eastwood = new Director("Clint", "Eastwood", 1930,
  Seq(highPlainsDrifter, outlawJoseyWales, unforgiven, granTorino, invictus))

val mcTiernan = Director("John", "McTiernan", 1951,
  Seq(predator, dieHard, huntForRedOctober, thomasCrownAffair))

val nolan = Director("Christopher", "Nolan", 1970,
  Seq(memento, darkKnight, inception))

val someGuy = Director("Just", "Some Guy", 1990,
  Seq())

val directors = Seq(eastwood, mcTiernan, nolan, someGuy)
// TODO: Write your code here!

def directedMoreThan(numberOfFilms: Int): Seq[Director] =
  directors.filter(_.films.length > numberOfFilms)

def bornBefore(year: Int): Seq[Director] =
  directors.filter(_.yearOfBirth < year)

def bornBeforeAndDirectedMoreThan(year: Int, numberOfFilms: Int): Seq[Director] =
  directors.filter(d => d.films.length > numberOfFilms && d.yearOfBirth < year)

// Starting with the definition of nolan, create a list containing the names of the films directed by Christopher
// Nolan.

println("Nolan's films")

nolan.films.map(_.name)

for(f <- nolan.films) yield f.name

// Star􀦞ng with the definition of directors, create a list containing the names of all films by all directors.

directors.flatMap(_.films).map(_.name)

for {
  d <- directors
  f <- d.films
} yield f.name

// Star􀦞ng with mcTiernan, find the date of the earliest McTiernan film.

mcTiernan.films.foldLeft(Int.MaxValue)((currentMin, film) => math.min(currentMin, film.yearOfRelease))

// Star􀦞ng with directors, find all films sorted by descending IMDB ra􀦞ng:

directors.flatMap(_.films).sortWith(_.imdbRating > _.imdbRating)

(for {
  d <- directors
  f <- d.films
} yield f).sortWith(_.imdbRating > _.imdbRating)

// Star􀦞ng with directors again, find the average score across all films:

directors.flatMap(_.films).map(_.imdbRating)
  .foldLeft(0.0)((prev, cur) => prev+cur) / directors.flatMap(_.films).length

// Finally, star􀦞ng with directors again, find the earliest film by any director:

directors.map(_.films.sortBy(_.yearOfRelease).headOption.getOrElse())

