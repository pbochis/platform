package uno.cod.platform.server.core.util;

import java.util.Random;

public class UsernameUtil {
    private static Random random = new Random();

    private static final String[] left = new String[] {
            "admiring",
            "adoring",
            "agitated",
            "amazing",
            "angry",
            "awesome",
            "backstabbing",
            "berserk",
            "big",
            "boring",
            "clever",
            "cocky",
            "compassionate",
            "condescending",
            "cranky",
            "desperate",
            "determined",
            "distracted",
            "dreamy",
            "drunk",
            "ecstatic",
            "elated",
            "elegant",
            "evil",
            "fervent",
            "focused",
            "furious",
            "gigantic",
            "gloomy",
            "goofy",
            "grave",
            "happy",
            "high",
            "hopeful",
            "hungry",
            "insane",
            "jolly",
            "jovial",
            "kickass",
            "lonely",
            "loving",
            "mad",
            "modest",
            "naughty",
            "nauseous",
            "nostalgic",
            "pedantic",
            "pensive",
            "prickly",
            "reverent",
            "romantic",
            "sad",
            "serene",
            "sharp",
            "sick",
            "silly",
            "sleepy",
            "small",
            "stoic",
            "stupefied",
            "suspicious",
            "tender",
            "thirsty",
            "tiny",
            "trusting",
    };

    private static final String[] right = new String[] {
            // https://en.wikipedia.org/wiki/Mu%E1%B8%A5ammad_ibn_J%C4%81bir_al-%E1%B8%A4arr%C4%81n%C4%AB_al-Batt%C4%81n%C4%AB
            "albattani",
            // https://en.wikipedia.org/wiki/Frances_E._Allen
            "allen",
            // https://en.wikipedia.org/wiki/June_Almeida
            "almeida",
            // https://en.wikipedia.org/wiki/Archimedes
            "archimedes",
            // https://en.wikipedia.org/wiki/Maria_Ardinghelli
            "ardinghelli",
            // https://en.wikipedia.org/wiki/Aryabhata
            "aryabhata",
            // https://en.wikipedia.org/wiki/Wanda_Austin
            "austin",
            // https://en.wikipedia.org/wiki/Charles_Babbage.
            "babbage",
            // https://en.wikipedia.org/wiki/Stefan_Banach
            "banach",
            // https://en.wikipedia.org/wiki/John_Bardeen
            "bardeen",
            // https://en.wikipedia.org/wiki/Jean_Bartik
            "bartik",
            // https://en.wikipedia.org/wiki/Laura_Bassi
            "bassi",
            // https://en.wikipedia.org/wiki/Alexander_Graham_Bell
            "bell",
            // https://en.wikipedia.org/wiki/Homi_J._Bhabha
            "bhabha",
            // https://en.wikipedia.org/wiki/Bh%C4%81skara_II#Calculus
            "bhaskara",
            // https://en.wikipedia.org/wiki/Elizabeth_Blackwell
            "blackwell",
            // https://en.wikipedia.org/wiki/Niels_Bohr.
            "bohr",
            // https://en.wikipedia.org/wiki/Kathleen_Booth
            "booth",
            // https://en.wikipedia.org/wiki/Anita_Borg
            "borg",
            // https://en.wikipedia.org/wiki/Satyendra_Nath_Bose
            "bose",
            // https://en.wikipedia.org/wiki/Evelyn_Boyd_Granville
            "boyd",
            // https://en.wikipedia.org/wiki/Brahmagupta#Zero
            "brahmagupta",
            // https://en.wikipedia.org/wiki/Walter_Houser_Brattain
            "brattain",
            // https://en.wikipedia.org/wiki/Emmett_Brown (thanks Brian Goff)
            "brown",
            // https://en.wikipedia.org/wiki/Rachel_Carson
            "carson",
            // https://en.wikipedia.org/wiki/Subrahmanyan_Chandrasekhar
            "chandrasekhar",
            // https://en.wikipedia.org/wiki/Jane_Colden
            "colden",
            // https://en.wikipedia.org/wiki/Gerty_Cori
            "cori",
            // https://en.wikipedia.org/wiki/Seymour_Cray
            "cray",
            // https://en.wikipedia.org/wiki/Marie_Curie.
            "curie",
            // https://en.wikipedia.org/wiki/Charles_Darwin.
            "darwin",
            // https://en.wikipedia.org/wiki/Leonardo_da_Vinci.
            "davinci",
            // https://en.wikipedia.org/wiki/Edsger_W._Dijkstra.
            "dijkstra",
            // https://en.wikipedia.org/wiki/Donna_Dubinsky
            "dubinsky",
            // https://en.wikipedia.org/wiki/Annie_Easley
            "easley",
            // https://en.wikipedia.org/wiki/Albert_Einstein
            "einstein",
            // https://en.wikipedia.org/wiki/Gertrude_Elion
            "elion",
            // https://en.wikipedia.org/wiki/Douglas_Engelbart
            "engelbart",
            // https://en.wikipedia.org/wiki/Euclid
            "euclid",
            // https://de.wikipedia.org/wiki/Leonhard_Euler
            "euler",
            // https://en.wikipedia.org/wiki/Pierre_de_Fermat
            "fermat",
            // https://en.wikipedia.org/wiki/Enrico_Fermi.
            "fermi",
            // https://en.wikipedia.org/wiki/Richard_Feynman
            "feynman",
            // Benjamin Franklin is famous for his experiments in electricity and the invention of the lightning rod.
            "franklin",
            // https://en.wikipedia.org/wiki/Galileo_Galilei
            "galileo",
            // https://en.wikipedia.org/wiki/Bill_Gates
            "gates",
            // https://en.wikipedia.org/wiki/Adele_Goldberg_(computer_scientist)
            "goldberg",
            // https://en.wikipedia.org/wiki/Adele_Goldstine
            "goldstine",
            // https://en.wikipedia.org/wiki/Shafi_Goldwasser
            "goldwasser",
            // http://jamesgolick.com/
            "golick",
            // https://en.wikipedia.org/wiki/Jane_Goodall
            "goodall",
            // https://en.wikipedia.org/wiki/Margaret_Hamilton_(scientist)
            "hamilton",
            // https://en.wikipedia.org/wiki/Stephen_Hawking
            "hawking",
            // https://en.wikipedia.org/wiki/Werner_Heisenberg
            "heisenberg",
            // https://en.wikipedia.org/wiki/Jaroslav_Heyrovsk%C3%BD
            "heyrovsky",
            // https://en.wikipedia.org/wiki/Dorothy_Hodgkin
            "hodgkin",
            // https://en.wikipedia.org/wiki/Erna_Schneider_Hoover
            "hoover",
            // https://en.wikipedia.org/wiki/Grace_Hopper
            "hopper",
            // https://en.wikipedia.org/wiki/Frances_Hugle
            "hugle",
            // https://en.wikipedia.org/wiki/Hypatia
            "hypatia",
            // https://en.wikipedia.org/wiki/Jang_Yeong-sil
            "jang",
            // https://en.wikipedia.org/wiki/Jean_Bartik
            "jennings",
            // https://en.wikipedia.org/wiki/Mary_Lou_Jepsen
            "jepsen",
            // https://en.wikipedia.org/wiki/Ir%C3%A8ne_Joliot-Curie
            "joliot",
            // https://en.wikipedia.org/wiki/Karen_Sp%C3%A4rck_Jones
            "jones",
            // https://en.wikipedia.org/wiki/A._P._J._Abdul_Kalam
            "kalam",
            // https://en.wikipedia.org/wiki/Susan_Kare
            "kare",
            // https://en.wikipedia.org/wiki/Mary_Kenneth_Keller
            "keller",
            // https://en.wikipedia.org/wiki/Har_Gobind_Khorana
            "khorana",
            // https://en.wikipedia.org/wiki/Jack_Kilby
            "kilby",
            // https://en.wikipedia.org/wiki/Maria_Margarethe_Kirch
            "kirch",
            // https://en.wikipedia.org/wiki/Donald_Knuth
            "knuth",
            // https://en.wikipedia.org/wiki/Sofia_Kovalevskaya
            "kowalevski",
            // https://en.wikipedia.org/wiki/Marie-Jeanne_de_Lalande
            "lalande",
            // https://en.wikipedia.org/wiki/Hedy_Lamarr
            "lamarr",
            // https://en.wikipedia.org/wiki/Mary_Leakey
            "leakey",
            // https://en.wikipedia.org/wiki/Henrietta_Swan_Leavitt
            "leavitt",
            // https://en.wikipedia.org/wiki/Ruth_Teitelbaum
            "lichterman",
            // https://en.wikipedia.org/wiki/Barbara_Liskov
            "liskov",
            // https://en.wikipedia.org/wiki/Ada_Lovelace (thanks James Turnbull)
            "lovelace",
            // https://en.wikipedia.org/wiki/Auguste_and_Louis_Lumi%C3%A8re
            "lumiere",
            // https://en.wikipedia.org/wiki/Mah%C4%81v%C4%ABra_(mathematician)
            "mahavira",
            // https://en.wikipedia.org/wiki/Maria_Mayer
            "mayer",
            // https://en.wikipedia.org/wiki/John_McCarthy_(computer_scientist)
            "mccarthy",
            // https://en.wikipedia.org/wiki/Barbara_McClintock
            "mcclintock",
            // https://en.wikipedia.org/wiki/Malcom_McLean
            "mclean",
            // https://en.wikipedia.org/wiki/Kathleen_Antonelli
            "mcnulty",
            // https://en.wikipedia.org/wiki/Lise_Meitner
            "meitner",
            // https://en.wikipedia.org/wiki/Carla_Meninsky
            "meninsky",
            // https://en.wikipedia.org/wiki/Johanna_Mestorf
            "mestorf",
            // https://en.wikipedia.org/wiki/Marvin_Minsky
            "minsky",
            // https://en.wikipedia.org/wiki/Maryam_Mirzakhani
            "mirzakhani",
            // https://en.wikipedia.org/wiki/Samuel_Morse
            "morse",
            // https://en.wikipedia.org/wiki/Ian_Murdock
            "murdock",
            // https://en.wikipedia.org/wiki/Isaac_Newton
            "newton",
            // https://en.wikipedia.org/wiki/Alfred_Nobel
            "nobel",
            // https://en.wikipedia.org/wiki/Emmy_Noether
            "noether",
            // http://www.businessinsider.com/poppy-northcutt-helped-apollo-astronauts-2014-12?op=1
            "northcutt",
            // https://en.wikipedia.org/wiki/Robert_Noyce
            "noyce",
            // https://en.wikipedia.org/wiki/P%C4%81%E1%B9%87ini#Comparison_with_modern_formal_systems
            "panini",
            // https://en.wikipedia.org/wiki/Ambroise_Par%C3%A9
            "pare",
            // https://en.wikipedia.org/wiki/Louis_Pasteur.
            "pasteur",
            // https://en.wikipedia.org/wiki/Cecilia_Payne-Gaposchkin
            "payne",
            // https://en.wikipedia.org/wiki/Radia_Perlman
            "perlman",
            // https://en.wikipedia.org/wiki/Rob_Pike
            "pike",
            // https://en.wikipedia.org/wiki/Henri_Poincar%C3%A9
            "poincare",
            // https://en.wikipedia.org/wiki/Laura_Poitras
            "poitras",
            // https://en.wikipedia.org/wiki/Ptolemy
            "ptolemy",
            // https://en.wikipedia.org/wiki/C._V._Raman
            "raman",
            // https://en.wikipedia.org/wiki/Srinivasa_Ramanujan
            "ramanujan",
            // https://en.wikipedia.org/wiki/Sally_Ride
            "ride",
            // https://en.wikipedia.org/wiki/Dennis_Ritchie
            "ritchie",
            // https://en.wikipedia.org/wiki/Wilhelm_R%C3%B6ntgen
            "roentgen",
            // https://en.wikipedia.org/wiki/Rosalind_Franklin
            "rosalind",
            // https://en.wikipedia.org/wiki/Meghnad_Saha
            "saha",
            // https://en.wikipedia.org/wiki/Jean_E._Sammet
            "sammet",
            // https://en.wikipedia.org/wiki/Carol_Shaw_(video_game_designer)
            "shaw",
            // https://en.wikipedia.org/wiki/Steve_Shirley
            "shirley",
            // https://en.wikipedia.org/wiki/William_Shockley
            "shockley",
            // https://en.wikipedia.org/wiki/Fran%C3%A7oise_Barr%C3%A9-Sinoussi
            "sinoussi",
            // https://en.wikipedia.org/wiki/Betty_Holberton
            "snyder",
            // https://en.wikipedia.org/wiki/Frances_Spence
            "spence",
            // https://en.wikiquote.org/wiki/Richard_Stallman
            "stallman",
            // https://en.wikipedia.org/wiki/Michael_Stonebraker
            "stonebraker",
            // https://en.wikipedia.org/wiki/Janese_Swanson
            "swanson",
            // https://en.wikiquote.org/wiki/Aaron_Swartz
            "swartz",
            // https://en.wikipedia.org/wiki/Bertha_Swirles
            "swirles",
            // https://en.wikipedia.org/wiki/Nikola_Tesla
            "tesla",
            // https://en.wikipedia.org/wiki/Ken_Thompson
            "thompson",
            // https://en.wikipedia.org/wiki/Linus_Torvalds
            "torvalds",
            // https://en.wikipedia.org/wiki/Alan_Turing.
            "turing",
            // https://en.wikipedia.org/wiki/Var%C4%81hamihira#Contributions
            "varahamihira",
            // https://en.wikipedia.org/wiki/Visvesvaraya
            "visvesvaraya",
            // https://en.wikipedia.org/wiki/Christiane_N%C3%BCsslein-Volhard
            "volhard",
            // https://en.wikipedia.org/wiki/Marlyn_Meltzer
            "wescoff",
            // https://en.wikipedia.org/wiki/Roberta_Williams
            "williams",
            // https://en.wikipedia.org/wiki/Sophie_Wilson
            "wilson",
            // https://en.wikipedia.org/wiki/Jeannette_Wing
            "wing",
            // https://en.wikipedia.org/wiki/Steve_Wozniak
            "wozniak",
            // https://en.wikipedia.org/wiki/Wright_brothers
            "wright",
            // https://en.wikipedia.org/wiki/Rosalyn_Sussman_Yalow
            "yalow",
            // https://en.wikipedia.org/wiki/Ada_Yonath
            "yonath",
    };

    public static String randomUsername() {
        return left[random.nextInt(left.length)] + "-" + right[random.nextInt(right.length)];
    }
}
