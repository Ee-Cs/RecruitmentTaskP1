package com.altapay.backend.services;

import com.altapay.backend.exceptions.MerchantApiServiceException;
import com.altapay.backend.model.ShopOrder;
import com.altapay.util.HttpUtil;
import com.altapay.util.XpathUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

public class MerchantApiService {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final HttpUtil httpUtil;
    private final XpathUtil xpathUtil;

    public MerchantApiService(HttpUtil httpUtil, XpathUtil xpathUtil) {
        this.httpUtil = httpUtil;
        this.xpathUtil = xpathUtil;
    }

    public CaptureResponse capturePayment(ShopOrder shopOrder) throws MerchantApiServiceException {
        logger.trace("MerchantApiService(): httpUtil[{}], xpathUtil[{}]", httpUtil, xpathUtil);
        // We don't need to implement this, write the rest of the code as if this has been implemented by use of the httpUtil and the xpathUtil
        final CaptureResponse captureResponse = new CaptureResponse();
        logger.info("capturePayment(): shopOrder[{}], captureResponse successful[{}]",
                shopOrder, captureResponse.wasSuccessful());
        return captureResponse;
    }

    public ReleaseResponse releasePayment(ShopOrder shopOrder) throws MerchantApiServiceException {
        // We don't need to implement this, write the rest of the code as if this has been implemented by use of the httpUtil and the xpathUtil
        final ReleaseResponse releaseResponse = new ReleaseResponse();
        logger.info("releasePayment(): shopOrder[{}] releaseResponse successful[{}]",
                shopOrder, releaseResponse.wasSuccessful());
        return releaseResponse;
    }
}
