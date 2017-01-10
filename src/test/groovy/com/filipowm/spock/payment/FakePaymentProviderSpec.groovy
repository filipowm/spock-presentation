package com.filipowm.spock.payment

import com.filipowm.spock.payment.infrastructure.FakePaymentProvider
import com.filipowm.spock.payment.model.PaymentStatus
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification


/**
 * @author Mateusz Filipowicz
 */
class FakePaymentProviderSpec extends Specification {

    @AutoCleanup('shutdown')
    @Shared
    def paymentProvider = new FakePaymentProvider()

    void 'payment is successful'() {
        given:
        def paymentProvider = new FakePaymentProvider()

        when:
        def result = paymentProvider.pay(1.1, 'asdt')

        then:
        result.status == PaymentStatus.OK
    }

}