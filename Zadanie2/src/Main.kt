// Перечисление для типов карт (помогает избежать опечаток)
enum class CardType {
    VK_PAY,
    MASTERCARD,
    MAESTRO,
    VISA,
    MIR
}

fun calculateCommission(
    cardType: CardType = CardType.VK_PAY,
    previousTransfers: Int = 0,
    transferAmount: Int
): Int {

    if (cardType == CardType.VK_PAY) {
        return 0
    }

    if (cardType == CardType.MASTERCARD || cardType == CardType.MAESTRO) {

        val totalMonthTransfers = previousTransfers + transferAmount

        return if (totalMonthTransfers <= 75_000) {
            0
        } else {
            (transferAmount * 0.006 + 20).toInt()
        }
    }

    if (cardType == CardType.VISA || cardType == CardType.MIR) {

        val percentCommission = transferAmount * 0.0075

        return if (percentCommission < 35) {
            35
        } else {
            percentCommission.toInt()
        }
    }

    return 0
}

fun main() {
    println("VK Pay: " + calculateCommission(transferAmount = 100_000))

    println("MasterCard (в лимите): " + calculateCommission(
        cardType = CardType.MASTERCARD,
        previousTransfers = 10_000,
        transferAmount = 5_000
    ))

    println("MasterCard (сверх лимита): " + calculateCommission(
        cardType = CardType.MASTERCARD,
        previousTransfers = 70_000,
        transferAmount = 10_000
    ))

    println("Visa (маленькая сумма): " + calculateCommission(
        cardType = CardType.VISA,
        transferAmount = 1_000
    ))

    println("Visa (крупная сумма): " + calculateCommission(
        cardType = CardType.VISA,
        transferAmount = 10_000
    ))
}