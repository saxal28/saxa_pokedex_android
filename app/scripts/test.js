const fetch = require('node-fetch');
const fs = require('fs');

const jsonPath = "/Users/alansax/development/saxapokedex/app/src/main/json"

const log = (message) => console.log(`\n===================\n\n${message}.....\n\n===================\n`)

// ===================
// API FETCH
// ===================

async function fetchUrl(url){
    const pokemonDataResponse = await fetch(url)
    const pokemonData = await pokemonDataResponse.json()
    return pokemonData
}

async function fetchAllPokemon() {
    const allPokemonResponse = await fetch("https://pokeapi.co/api/v2/pokemon?limit=20")
    const allPokemon = await allPokemonResponse.json()
    return allPokemon.results
}

const fetchPokemonData = async (pokemonName) => fetchUrl(`https://pokeapi.co/api/v2/pokemon/${pokemonName}`)

const fetchPokemonSpeciesData = async (pokemonName) => fetchUrl(`https://pokeapi.co/api/v2/pokemon-species/${pokemonName}`)

const fetchPokemonEvolutionData = async (pokemonEvolutionUrl) => fetchUrl(pokemonEvolutionUrl)

// ===================
// CREATE JSON
// ===================

function createAllPokemonDetailJson(allPokemon, allPokemonWithDetails, allPokemonWithSpeciesDetails) {
    // log("ALL LIST POKEMON")
    // console.log(allPokemon)

    // log("ALL POKEMON WITH DETAILS")
    // console.log(allPokemonWithDetails)

    // log("ALL POKEMON WITH SPECIES DETAILS")
    // console.log(allPokemonWithSpeciesDetails)

    allPokemon.map(async (pokemon, index) => {
        const pokemonDetail = allPokemonWithDetails[index]
        const pokemonSpecies = allPokemonWithSpeciesDetails[index]

        const { name, id, order, game_indices, sprites, types, height, weight, base_experience, moves, stats } = pokemonDetail

        const { base_happiness, capture_rate, egg_groups, flavor_text_entries, evolution_chain, evolves_from_species } = pokemonSpecies

        const {versions, ...restSprites} = sprites 

        const data = {
            name, id, order, height, weight, base_experience,
            base_happiness, capture_rate, 
            
            sprites: restSprites, 
            types, 
            egg_groups, 
            stats,
            game_indices,
            moves,
            flavor_text_entries, 
            evolution_chain, evolves_from_species
        }

        data.evolution_chain_fetched = await fetchPokemonEvolutionData(evolution_chain.url)

        createJSONFile(`/pokemon-${id}.json`, data)
    })
}

function createAllPokemonListJson(allPokemon, allPokemonWithDetails) {

    const mapped = allPokemon.map((pokemon, index) => {
        const pokemonDetail = allPokemonWithDetails[index]

        const {name, id, order, game_indices, sprites, types} = pokemonDetail
        const {versions, ...restSprites} = sprites 

        return ({
            name,
            id,
            order,
            game_indices, 
            sprites: restSprites,
            types
        })
    })

    createJSONFile("/pokemon.json", mapped)
}

function createJSONFile(url, resultData) {
    const pokemonJsonPath = jsonPath + url
    const data = JSON.stringify({ result: resultData }, null, 2)

    fs.writeFileSync(pokemonJsonPath, data)

    const result = JSON.parse(fs.readFileSync(pokemonJsonPath))
    log(url)
    console.log(result)
}

// ===================
// MAIN
// ===================

async function fetchData() {
    // Get all pokemon
    const allPokemon = await fetchAllPokemon()

    // Get pokemon detail sub query for each request
    const detailRequests = []
    const speciesRequests = []
    allPokemon.forEach(pokemon => {
        detailRequests.push(fetchPokemonData(pokemon.name))
        speciesRequests.push(fetchPokemonSpeciesData(pokemon.name))
    })
    const allPokemonWithDetails = await Promise.all(detailRequests)
    const allPokemonWithSpeciesDetails = await Promise.all(speciesRequests)

    createAllPokemonListJson(allPokemon, allPokemonWithDetails, allPokemonWithSpeciesDetails)
    createAllPokemonDetailJson(allPokemon, allPokemonWithDetails, allPokemonWithSpeciesDetails)

    log("finished")
}
log("starting")
fetchData()