import {RadioType} from "../objects/enums/RadioType";

export const getTextsByType = (radioType: RadioType) => {
    switch (radioType) {
        case RadioType.OPERATION:
            return ["Pobieranie Danych", "Dodawanie Rekordów", "Aktualizowanie Rekordów", "Usuwanie Rekordów"]
        case RadioType.TYPE:
            return ["Brak dodatkowych kryteriów", "Prosta selekcja", "Grupowanie z prostą selekcją", "Sortowanie"]
        case RadioType.QUANTITY:
        default:
            return ["100", "1000", "5000", "15000"]

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
