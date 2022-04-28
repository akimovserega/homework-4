package ru.netology

const val MAESTRO_MASTER_NO_FEE_MIN = 300_00
const val MAESTRO_MASTER_NO_FEE_MAX = 75_000_00
const val MAESTRO_MASTER_FEE = 0.6F / 100
const val MAESTRO_MASTER_CONST_FEE = 20_00
const val MAESTRO_MASTER_TEMP_FEE = 0

const val VISA_MIR_FEE = 0.75F / 100
const val VISA_MIR_MIN_FEE = 35_00
const val VK_PAY_FEE = 0

const val VK_PAY_MAX_LIM = 15_000_00
const val VK_PAY_MAX_MONTHLIM = 40_000_00
const val CARD_LIM = 150_000_00
const val CARD_MONTHLIM = 600_000_00


fun main() {

    // два вида карт и VK pay
    var type: String = "Mastercard"
    var monthAmount: Int = 100_000_00
    var currentAmount: Int = 1_000_00


    var fee = calculateFee(type, monthAmount, currentAmount)
    printFee(type, currentAmount, monthAmount, fee)

    monthAmount = 70_000_00
    currentAmount = 15_000_00
    fee = calculateFee(type, monthAmount, currentAmount)
    printFee(type, currentAmount, monthAmount, fee)

    type = "Visa"
    monthAmount = 100_000_00
    currentAmount = 15_000_00
    fee = calculateFee(type, monthAmount, currentAmount)
    printFee(type, currentAmount, monthAmount, fee)

    monthAmount = 10_000_00
    currentAmount = 1_000_00
    fee = calculateFee(type, monthAmount, currentAmount)
    printFee(type, currentAmount, monthAmount, fee)


    currentAmount = 15_000_00
    fee = calculateFee(currentAmount = currentAmount)
    printFee(amount = currentAmount, fee = fee)

    monthAmount = 30_000_00
    currentAmount = 15_000_00
    fee = calculateFee(monthAmount = monthAmount, currentAmount = currentAmount)
    printFee(amount = currentAmount, total = monthAmount, fee = fee)

}

fun calculateFee(cardType: String = "Vk Pay", monthAmount: Int = 0, currentAmount: Int): Int {
    var fee: Int = 0;

    if (!checkLimits(cardType, monthAmount, currentAmount)) {
        //Превышен лимит на перевод

        return -1

    } else {
        return when (cardType) {
            "Mastercard", "Maestro" ->
                if (currentAmount >= MAESTRO_MASTER_NO_FEE_MIN && currentAmount + monthAmount <= MAESTRO_MASTER_NO_FEE_MAX)
                    MAESTRO_MASTER_TEMP_FEE
                else (currentAmount * MAESTRO_MASTER_FEE + MAESTRO_MASTER_CONST_FEE).toInt()
            "Visa", "Mir" ->
                if (currentAmount * VISA_MIR_FEE > VISA_MIR_MIN_FEE) (currentAmount * VISA_MIR_FEE).toInt()
                else VISA_MIR_MIN_FEE
            "Vk Pay" -> VK_PAY_FEE
            else -> 0
        }
    }
}


fun checkLimits(cardType: String = "Vk Pay", monthAmount: Int = 0, currentAmount: Int): Boolean {

    return when (cardType) {
        "Mastercard", "Maestro", "Visa", "Mir" ->
            (currentAmount <= CARD_LIM) && (monthAmount + currentAmount <= CARD_MONTHLIM)
        "Vk Pay" -> (currentAmount <= VK_PAY_MAX_LIM) && (monthAmount + currentAmount <= VK_PAY_MAX_MONTHLIM)
        else -> {
            true
        }
    }
}

fun printFee(type: String = "Vk Pay", amount: Int, total: Int = 0, fee: Int): Boolean {
    println(
        "Выбрана платежная система: $type. Сумма перевода  ${centFormat(amount)}, сумма предыдущих" +
                " переводов ${centFormat(total)}, " +
                "${if (fee >= 0) "итого Ваша комиссия составляет ${centFormat(fee)}" else "превышен лимит на перевод"}"
    )

    return if (fee >= 0) true else false
}

fun centFormat(amount: Int): String {
    return if (amount % 100 > 0) "${amount / 100}.${if ((amount % 100) / 10 > 0) "" else "0"}" +
            "${amount % 100}" else "${amount / 100}"
}