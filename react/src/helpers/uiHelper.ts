import {RadioType} from "../objects/enums/RadioType";

export const getTextsByType = (radioType: RadioType) => {
    switch (radioType) {
        case RadioType.OPERATION:
            return ["Pobieranie Danych", "Dodawanie Rekordów", "Aktualizowanie Rekordów", "Usuwanie Rekordów"]
        case RadioType.TYPE:
            return ["Brak dodatkowych kryteriów", "Proste kryterium selekcji", "Grupowanie", "Sortowanie"]
        case RadioType.QUANTITY:
        default:
            return ["10", "100", "1000", "10000"]

    }
}
export const getTitleByType = (radioType: RadioType) => {
    switch (radioType) {
        case RadioType.OPERATION:
            return "Nazwa operacji:"
        case RadioType.TYPE:
            return "Rodzaj zapytania:"
        case RadioType.QUANTITY:
        default:
            return "Ilość zapytań:"

    }
};