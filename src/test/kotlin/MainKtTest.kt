import org.junit.Test

import org.junit.Assert.*
import ru.netology.calculateFee
import ru.netology.centFormat
import ru.netology.checkLimits
import ru.netology.printFee

class MainKtTest {

    @Test
    fun calculate_fee_card_mir() {
        //arrange
        val type = "Mir"
        val amount = 1_000_00
        val total = 20_000_00
        val expect = 35_00

        //act
        val result = calculateFee(type, total, amount)

        //assert
        assertEquals(expect, result)
    }

    @Test
    fun calculate_fee_card_visa() {
        //arrange
        val type = "Mir"
        val amount = 10_000_00
        val total = 100_000_00
        val expect = 75_00

        //act
        val result = calculateFee(type, total, amount)

        //assert
        assertEquals(expect, result)
    }

    @Test
    fun calculate_fee_default() {
        //arrange

        val amount = 15_000_00
        val total = 10_000_00
        val expect = 0

        //act
        val result = calculateFee(monthAmount= total, currentAmount=amount)

        //assert
        assertEquals(expect, result)
    }

    @Test
    fun calculate_no_fee() {
        //arrange
        val type = "Mastercard"
        val amount = 55_000_00
        val total = 20_000_00
        val expect = 0

        //act
        val result = calculateFee(type, total, amount)

        //assert
        assertEquals(expect, result)
    }

    @Test
    fun calculate_fee_with_limits() {
        // cardType: String = "Vk Pay", monthAmount: Int = 0, currentAmount: Int

        //arrange
        val type = "Mir"
        val amount = 1_00
        val total = 1_000_000_00
        val expect = -1

        //act
        val result = calculateFee(type, total, amount)

        //assert
        assertEquals(expect, result)
    }

    @Test
    fun calculate_fee_alternative_pay() {
        //arrange
        val type = "MirPay"
        val amount = 15_000_00
        val total = 10_000_00
        val expect = 0

        //act
        val result = calculateFee(type, total, amount)

        //assert
        assertEquals(expect, result)
    }

    @Test
    fun check_limits_small() {
        //arrange
        val type = "Mastercard"
        val amount = 1_000_31
        val total = 10_000_00
        val expect = true

        //act
        val result = checkLimits(type, total, amount)

        //assert
        assertEquals(expect, result)
    }

    @Test
    fun check_limits_big() {
        //arrange
        val type = "Mastercard"
        val amount = 200_000_00
        val total = 10_000_00
        val expect = false

        //act
        val result = checkLimits(type, total, amount)

        //assert
        assertEquals(expect, result)
    }

    @Test
    fun check_limits_default() {
        //arrange

        val amount = 20_000_00
        val total = 10_000_00
        val expect = false

        //act
        val result = checkLimits(monthAmount = total, currentAmount = amount)

        //assert
        assertEquals(expect, result)
    }

    @Test
    fun print_no_fee() {
        //arrange
        val type = "Mastercard"
        val amount = 100_31
        val fee = -1
        val expect = false

        //act
        val result = printFee(type, amount = amount, fee = fee)

        //assert
        assertEquals(expect, result)
    }

    @Test
    fun print_fee_default_pay() {
        //arrange
        val amount = 100_00
        val total = 10_000_00
        val fee = 10_00
        val expect = true
        //act

        val result = printFee(amount = amount, total = total, fee = fee)

        //assert
        assertEquals(expect, result)
    }

    @Test
    fun cent_format_with_cent() {
        //arrange
        val amount = 10_05
        val expect = "10.05"

        //act
        val result = centFormat(amount)

        //assert
        assertEquals(expect, result)
    }

    @Test
    fun cent_format_without_cent() {
        //arrange
        val amount = 100_00
        val expect = "100"

        //act
        val result = centFormat(amount)

        //assert
        assertEquals(expect, result)

    }
}