package com.aiglesiaspubill.androidavanzadofinal.data.utils

import com.aiglesiaspubill.androidavanzadofinal.data.local.model.HeroLocal
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.HeroRemote
import com.aiglesiaspubill.androidavanzadofinal.data.remote.response.LocationRemote
import com.aiglesiaspubill.androidavanzadofinal.domain.Hero
import com.aiglesiaspubill.androidavanzadofinal.domain.Location


fun generateToken(): String {
    return ""
}

fun generateHerosRemote(): List<HeroRemote> {
    return (0 until 10).map { HeroRemote(
        "14BB8E98-6586-4EA7-B4D7-35D6A63F5AA3",
        "Maestro Roshi",
        "https://cdn.alfabetajuega.com/alfabetajuega/2020/06/Roshi.jpg?width=300",
        "Es un maestro de artes marciales que tiene una escuela, donde entrenará a Goku y Krilin para los Torneos de Artes Marciales." +
                "Aún en los primeros episodios había un toque de tradición y disciplina, muy bien representada por el maestro." +
                "Pero Muten Roshi es un anciano extremadamente pervertido con las chicas jóvenes, una actitud que se utilizaba en escenas divertidas en los años 80." +
                "En su faceta de experto en artes marciales, fue quien le enseñó a Goku técnicas como el Kame Hame Ha",
        false) }
}

fun generateHeroRemote(): HeroRemote {
    return HeroRemote(
        "14BB8E98-6586-4EA7-B4D7-35D6A63F5AA3",
        "Maestro Roshi",
        "https://cdn.alfabetajuega.com/alfabetajuega/2020/06/Roshi.jpg?width=300",
        "Es un maestro de artes marciales que tiene una escuela, donde entrenará a Goku y Krilin para los Torneos de Artes Marciales." +
                "Aún en los primeros episodios había un toque de tradición y disciplina, muy bien representada por el maestro." +
                "Pero Muten Roshi es un anciano extremadamente pervertido con las chicas jóvenes, una actitud que se utilizaba en escenas divertidas en los años 80." +
                "En su faceta de experto en artes marciales, fue quien le enseñó a Goku técnicas como el Kame Hame Ha",
        false)
}

fun generateHerosLocal(): List<HeroLocal> {
    return (0 until 10).map { HeroLocal(
        "14BB8E98-6586-4EA7-B4D7-35D6A63F5AA3",
        "Maestro Roshi",
        "https://cdn.alfabetajuega.com/alfabetajuega/2020/06/Roshi.jpg?width=300",
        "Es un maestro de artes marciales que tiene una escuela, donde entrenará a Goku y Krilin para los Torneos de Artes Marciales." +
                "Aún en los primeros episodios había un toque de tradición y disciplina, muy bien representada por el maestro." +
                "Pero Muten Roshi es un anciano extremadamente pervertido con las chicas jóvenes, una actitud que se utilizaba en escenas divertidas en los años 80." +
                "En su faceta de experto en artes marciales, fue quien le enseñó a Goku técnicas como el Kame Hame Ha",
        false) }
}

fun generateHeros(): List<Hero> {
    return (0 until 10).map { Hero(
        "14BB8E98-6586-4EA7-B4D7-35D6A63F5AA3",
        "Maestro Roshi",
        "https://cdn.alfabetajuega.com/alfabetajuega/2020/06/Roshi.jpg?width=300",
        "Es un maestro de artes marciales que tiene una escuela, donde entrenará a Goku y Krilin para los Torneos de Artes Marciales." +
                "Aún en los primeros episodios había un toque de tradición y disciplina, muy bien representada por el maestro." +
                "Pero Muten Roshi es un anciano extremadamente pervertido con las chicas jóvenes, una actitud que se utilizaba en escenas divertidas en los años 80." +
                "En su faceta de experto en artes marciales, fue quien le enseñó a Goku técnicas como el Kame Hame Ha",
        false) }
}

fun generateHero(): List<Hero> {
    return (0 until 1).map { Hero(
        "14BB8E98-6586-4EA7-B4D7-35D6A63F5AA3",
        "Maestro Roshi",
        "https://cdn.alfabetajuega.com/alfabetajuega/2020/06/Roshi.jpg?width=300",
        "Es un maestro de artes marciales que tiene una escuela, donde entrenará a Goku y Krilin para los Torneos de Artes Marciales." +
                "Aún en los primeros episodios había un toque de tradición y disciplina, muy bien representada por el maestro." +
                "Pero Muten Roshi es un anciano extremadamente pervertido con las chicas jóvenes, una actitud que se utilizaba en escenas divertidas en los años 80." +
                "En su faceta de experto en artes marciales, fue quien le enseñó a Goku técnicas como el Kame Hame Ha",
        false) }
}

fun generateLocations(): List<Location> {
    return (0 until 10).map { Location(
        "AB3A873C-37B4-4FDE-A50F-8014D40D94FE",
        "-2.4746262",
        "36.8415268",
        "2022-09-11T00:00:00Z") }
}

fun generateRemoteLocations(): List<LocationRemote> {
    return (0 until 10).map { LocationRemote(
        "AB3A873C-37B4-4FDE-A50F-8014D40D94FE",
        "-2.4746262",
        "36.8415268",
        "2022-09-11T00:00:00Z") }
}


