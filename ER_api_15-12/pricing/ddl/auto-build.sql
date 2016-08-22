
    create table BalanceImpact (
        "KEY" number(19,0) not null,
        chg_res number(10,0),
        fixedAmount double precision not null,
        notificationThreshold number(10,0) not null,
        priceChangeStartDate timestamp,
        rate double precision not null,
        scaledAmount double precision not null,
        "pricePoint_KEY" number(19,0) not null,
        primary key ("KEY")
    );

    create table PACKAGE_SERVICE (
        PACKAGE_KEY number(19,0) not null,
        SERVICE_KEY number(19,0) not null
    );

    create table PaymentContent (
        "KEY" number(19,0) not null,
        category varchar2(255 char),
        description varchar2(255 char),
        itemVolume varchar2(255 char),
        merchant varchar2(255 char),
        merchantDescription varchar2(255 char),
        promotion varchar2(255 char),
        serviceType varchar2(255 char),
        primary key ("KEY")
    );

    create table PricePoint (
        "KEY" number(19,0) not null,
        ROUND_NTH_EXPRESS_PATTERN varchar2(255 char),
        alignWithExternal number(1,0) not null,
        alwaysValidateMsisdn number(1,0) not null,
        fairUsageLimit number(10,0) not null,
        fairUsagePeriod number(10,0) not null,
        fairUsagePeriodUnit varchar2(255 char),
        hideForPurchaseOptions number(1,0) not null,
        includeServiceForPackageFUP number(1,0) not null,
        isZeroCostIgnore number(1,0) not null,
        linkedByTrialPricepoint number(1,0) not null,
        mAccessDuration double precision not null,
        mBasePricePoint number(1,0) not null,
        mCancellation number(1,0) not null,
        mContentId varchar2(255 char),
        mDuplicateSubscription number(1,0) not null,
        mExtensionPeriod number(10,0) not null,
        mFixedRecurrence number(19,0) not null,
        mFixedRecurringPricePoint number(1,0) not null,
        mForcedPurchase number(1,0) not null,
        mGlid varchar2(255 char),
        mHistoric number(1,0) not null,
        mId varchar2(255 char),
        mImpacts raw(255),
        mInteractiveFlag varchar2(255 char),
        mIsArchived number(1,0) not null,
        mIsDiscount number(1,0) not null,
        mIsOriginal number(1,0) not null,
        mIsVolumeType number(1,0) not null,
        mMinSubPeriod number(10,0) not null,
        mMonthEndSubscription varchar2(255 char),
        mOrder number(10,0) not null,
        mPackageId varchar2(255 char),
        mPaymentHandler varchar2(255 char),
        mPenaltyCharges double precision not null,
        mPricepointIdLink varchar2(255 char),
        mPricingText1 varchar2(255 char),
        mPricingText2 varchar2(255 char),
        mPromoText varchar2(255 char),
        mProtectedFk varchar2(255 char),
        mPurchaseExpiryDate timestamp,
        mPurchaseFixedExpiryDate timestamp,
        mPurchaseStartDate timestamp,
        mRateWithoutTax double precision not null,
        mReceipting number(1,0) not null,
        mReceiptingAttribute varchar2(255 char),
        mReserveOnly number(1,0) not null,
        mStandardRate double precision not null,
        mTax raw(255),
        offFootPrintPrice double precision not null,
        onFootPrintPrice double precision not null,
        pricingTextTemplateName1 varchar2(255 char),
        pricingTextTemplateName2 varchar2(255 char),
        pricingTextTemplateNameRoaming varchar2(255 char),
        recurrenceDay number(10,0) not null,
        renewalsUntilLinkedPricepoint number(10,0) not null,
        translatedPricingText varchar2(255 char),
        translatedPricingText1 varchar2(255 char),
        translatedPricingText2 varchar2(255 char),
        usageTime number(19,0) not null,
        useStaticValues number(1,0) not null,
        "pack_KEY" number(19,0) not null,
        primary key ("KEY")
    );

    create table Priceplan (
        "KEY" number(19,0) not null,
        description varchar2(255 char),
        name varchar2(255 char),
        opco number(10,0),
        primary key ("KEY")
    );

    create table Tax (
        key number(19,0) not null,
        name varchar2(255 char),
        taxCode varchar2(255 char),
        primary key (key)
    );

    create table TaxRate (
        key number(19,0) not null,
        endDate timestamp,
        startDate timestamp,
        value double precision not null,
        tax_key number(19,0) not null,
        primary key (key)
    );

    create table package (
        "KEY" number(19,0) not null,
        disallowsubpch varchar2(255 char),
        hasBalanceImpactsWithDate number(1,0) not null,
        hasParentSub number(1,0) not null,
        hasParentSubSusResProv number(1,0),
        hasPricePointsWithDate number(1,0) not null,
        hasPromosWithDate number(1,0) not null,
        hasTaxRateWithDate number(1,0) not null,
        goodwillCredit number(1,0),
        isPackageModel number(1,0),
        kpiPackageProductCategory varchar2(255 char),
        kpiPackageType varchar2(255 char),
        logoId varchar2(255 char),
        DataVoiceTariffInclusive number(1,0),
        description varchar2(255 char),
        DisallowCancellations number(1,0),
        DisallowPrerate number(1,0),
        DynamicDefault number(1,0),
        DynamicProtectedValue varchar2(255 char),
        ExpressPurchase number(1,0),
        Id varchar2(255 char),
        NominalValue double precision,
        NonRefundableDescription varchar2(255 char),
        notificationCategory varchar2(255 char),
        PackageClass varchar2(255 char),
        PackageType varchar2(255 char),
        parentPackage number(1,0),
        parentPackageId varchar2(255 char),
        PricePointOrder number(1,0),
        Priority number(10,0),
        protectedType varchar2(255 char),
        PurchaseMethod varchar2(255 char),
        RateCardServiceId varchar2(255 char),
        RecieptingFlag number(1,0),
        Refundable number(1,0),
        reserveOnly number(1,0),
        RevenueShareByUsage number(1,0),
        SalesModel varchar2(255 char),
        SuperPackage number(1,0),
        taxcode varchar2(255 char),
        UpSell number(1,0),
        UpsellDiscountProrated number(1,0),
        url varchar2(255 char),
        UseBeingDeprovisionedStatus number(1,0),
        useRateCardService number(1,0),
        name varchar2(255 char),
        parentSubId varchar2(255 char),
        parentSubStatus number(10,0) not null,
        pricingText1 varchar2(255 char),
        pricingText2 varchar2(255 char),
        svcsNotInPackFUPolicyList varchar2(255 char),
        "priceplan_KEY" number(19,0),
        primary key ("KEY")
    );

    create table service (
        "KEY" number(19,0) not null,
        description varchar2(255 char),
        ChargeableBy varchar2(255 char),
        ContentCategory varchar2(255 char),
        ContentItem varchar2(255 char),
        ContentSubCategory varchar2(255 char),
        DealName varchar2(255 char),
        DeliveryMechanism varchar2(255 char),
        DistributionChannel varchar2(255 char),
        ExpiredPackageService number(1,0),
        FixedUsageAmount double precision,
        GlobalHandler number(1,0),
        GlobalHandlerNotification number(1,0),
        HasDynamicDefault number(1,0),
        HasExpress number(1,0),
        HasSuperPackage number(1,0),
        HighVolumeInterfaceLevel number(10,0),
        id varchar2(255 char),
        IndirectValue varchar2(255 char),
        IndirectValueFormat varchar2(255 char),
        defaultservice number(1,0),
        MicroService number(1,0),
        NonRefundableDescription varchar2(255 char),
        NotificationCategory varchar2(255 char),
        ProductCategory varchar2(255 char),
        ProductFk number(19,0),
        ProductSelfRegulation varchar2(255 char),
        ProductSubCategory1 varchar2(255 char),
        ProductSubCategory2 varchar2(255 char),
        ProductWholesalePrice varchar2(255 char),
        PromoValue varchar2(255 char),
        PromoValueFormat varchar2(255 char),
        provisioningSystem varchar2(255 char),
        provisioningTag varchar2(255 char),
        ProvisionOnUsage number(1,0),
        ReIssuePermittedFlag number(1,0),
        Refundable number(1,0),
        reserveOnly number(1,0),
        ReturnAllCatalogueServicesInfo number(1,0),
        ReturnTrialOptionsOnly number(1,0),
        SalesModel varchar2(255 char),
        ServiceCategory varchar2(255 char),
        ServiceClass varchar2(255 char),
        ServiceShareOverride number(1,0),
        ServiceType varchar2(255 char),
        taxcode varchar2(255 char),
        url varchar2(255 char),
        usage_being_prov number(1,0),
        UsageId varchar2(255 char),
        UseRateCard number(1,0),
        name varchar2(255 char),
        pricingText1 varchar2(255 char),
        pricingText2 varchar2(255 char),
        "priceplan_KEY" number(19,0) not null,
        primary key ("KEY")
    );

    alter table BalanceImpact 
        add constraint FKB7E3322540BFC9 
        foreign key ("pricePoint_KEY") 
        references PricePoint;

    alter table PACKAGE_SERVICE 
        add constraint FKCDB8337C3A6372EC 
        foreign key (SERVICE_KEY) 
        references service;

    alter table PACKAGE_SERVICE 
        add constraint FKCDB8337C3D231D8E 
        foreign key (PACKAGE_KEY) 
        references package;

    alter table PricePoint 
        add constraint FK1EC5F5E733B0ADC1 
        foreign key ("pack_KEY") 
        references package;

    alter table TaxRate 
        add constraint FK7D34B0B3E06C59B 
        foreign key (tax_key) 
        references Tax;

    alter table package 
        add constraint FKCFE53446583E3EC9 
        foreign key ("priceplan_KEY") 
        references Priceplan;

    alter table service 
        add constraint FK7643C6B5583E3EC9 
        foreign key ("priceplan_KEY") 
        references Priceplan;

    create sequence hibernate_sequence;
