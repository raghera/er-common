//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.13 at 04:57:40 PM BST 
//


package com.vodafone.global.er.decoupling.binding.response.impl;

public class DateTimeTierFullTypeImpl implements com.vodafone.global.er.decoupling.binding.response.DateTimeTierFullType, com.sun.xml.bind.JAXBObject, com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallableObject, com.vodafone.global.er.decoupling.binding.response.impl.runtime.XMLSerializable, com.vodafone.global.er.decoupling.binding.response.impl.runtime.ValidatableObject
{

    protected com.vodafone.global.er.decoupling.binding.response.DayRangeFullType _HoursOfDay;
    protected com.vodafone.global.er.decoupling.binding.response.RangeValueFullType _DaysOfMonth;
    protected com.vodafone.global.er.decoupling.binding.response.RangeValueFullType _DaysOfWeek;
    protected boolean has_IsPromotion;
    protected boolean _IsPromotion;
    protected java.lang.String _Id;
    protected com.vodafone.global.er.decoupling.binding.response.RangeValueFullType _MonthsOfYear;
    public final static java.lang.Class version = (com.vodafone.global.er.decoupling.binding.response.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (com.vodafone.global.er.decoupling.binding.response.DateTimeTierFullType.class);
    }

    public com.vodafone.global.er.decoupling.binding.response.DayRangeFullType getHoursOfDay() {
        return _HoursOfDay;
    }

    public void setHoursOfDay(com.vodafone.global.er.decoupling.binding.response.DayRangeFullType value) {
        _HoursOfDay = value;
    }

    public com.vodafone.global.er.decoupling.binding.response.RangeValueFullType getDaysOfMonth() {
        return _DaysOfMonth;
    }

    public void setDaysOfMonth(com.vodafone.global.er.decoupling.binding.response.RangeValueFullType value) {
        _DaysOfMonth = value;
    }

    public com.vodafone.global.er.decoupling.binding.response.RangeValueFullType getDaysOfWeek() {
        return _DaysOfWeek;
    }

    public void setDaysOfWeek(com.vodafone.global.er.decoupling.binding.response.RangeValueFullType value) {
        _DaysOfWeek = value;
    }

    public boolean isIsPromotion() {
        return _IsPromotion;
    }

    public void setIsPromotion(boolean value) {
        _IsPromotion = value;
        has_IsPromotion = true;
    }

    public java.lang.String getId() {
        return _Id;
    }

    public void setId(java.lang.String value) {
        _Id = value;
    }

    public com.vodafone.global.er.decoupling.binding.response.RangeValueFullType getMonthsOfYear() {
        return _MonthsOfYear;
    }

    public void setMonthsOfYear(com.vodafone.global.er.decoupling.binding.response.RangeValueFullType value) {
        _MonthsOfYear = value;
    }

    public com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallingContext context) {
        return new com.vodafone.global.er.decoupling.binding.response.impl.DateTimeTierFullTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(com.vodafone.global.er.decoupling.binding.response.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_IsPromotion) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "IsPromotion"));
        }
        context.startElement("", "id");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _Id), "Id");
        } catch (java.lang.Exception e) {
            com.vodafone.global.er.decoupling.binding.response.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        context.startElement("", "is-promotion");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(javax.xml.bind.DatatypeConverter.printBoolean(((boolean) _IsPromotion)), "IsPromotion");
        } catch (java.lang.Exception e) {
            com.vodafone.global.er.decoupling.binding.response.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        if (_MonthsOfYear!= null) {
            context.startElement("", "months-of-year");
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _MonthsOfYear), "MonthsOfYear");
            context.endNamespaceDecls();
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _MonthsOfYear), "MonthsOfYear");
            context.endAttributes();
            context.childAsBody(((com.sun.xml.bind.JAXBObject) _MonthsOfYear), "MonthsOfYear");
            context.endElement();
        }
        if (_DaysOfMonth!= null) {
            context.startElement("", "days-of-month");
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _DaysOfMonth), "DaysOfMonth");
            context.endNamespaceDecls();
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _DaysOfMonth), "DaysOfMonth");
            context.endAttributes();
            context.childAsBody(((com.sun.xml.bind.JAXBObject) _DaysOfMonth), "DaysOfMonth");
            context.endElement();
        }
        if (_DaysOfWeek!= null) {
            context.startElement("", "days-of-week");
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _DaysOfWeek), "DaysOfWeek");
            context.endNamespaceDecls();
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _DaysOfWeek), "DaysOfWeek");
            context.endAttributes();
            context.childAsBody(((com.sun.xml.bind.JAXBObject) _DaysOfWeek), "DaysOfWeek");
            context.endElement();
        }
        if (_HoursOfDay!= null) {
            context.startElement("", "hours-of-day");
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _HoursOfDay), "HoursOfDay");
            context.endNamespaceDecls();
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _HoursOfDay), "HoursOfDay");
            context.endAttributes();
            context.childAsBody(((com.sun.xml.bind.JAXBObject) _HoursOfDay), "HoursOfDay");
            context.endElement();
        }
    }

    public void serializeAttributes(com.vodafone.global.er.decoupling.binding.response.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_IsPromotion) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "IsPromotion"));
        }
    }

    public void serializeURIs(com.vodafone.global.er.decoupling.binding.response.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        if (!has_IsPromotion) {
            context.reportError(com.sun.xml.bind.serializer.Util.createMissingObjectError(this, "IsPromotion"));
        }
    }

    public java.lang.Class getPrimaryInterface() {
        return (com.vodafone.global.er.decoupling.binding.response.DateTimeTierFullType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
+"expandedExpq\u0000~\u0000\u0002xpppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0000ppsr\u0000\'com."
+"sun.msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000"
+"\u001fLcom/sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv.grammar.Elem"
+"entExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000\fcontentMode"
+"lq\u0000~\u0000\u0002xq\u0000~\u0000\u0003pp\u0000sq\u0000~\u0000\u0000ppsr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000"
+"\u0004namet\u0000\u001dLcom/sun/msv/util/StringPair;xq\u0000~\u0000\u0003sr\u0000\u0011java.lang.Boo"
+"lean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000#com.sun.msv.datatype.xsd.Stri"
+"ngType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxr\u0000*com.sun.msv.datatype.xs"
+"d.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datatype.xsd.C"
+"oncreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd.XSDatatyp"
+"eImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUrit\u0000\u0012Ljava/lang/String;L\u0000\btypeN"
+"ameq\u0000~\u0000\u0019L\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/WhiteSpace"
+"Processor;xpt\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0006stringsr\u00005"
+"com.sun.msv.datatype.xsd.WhiteSpaceProcessor$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001"
+"\u0002\u0000\u0000xp\u0001sr\u00000com.sun.msv.grammar.Expression$NullSetExpression\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003q\u0000~\u0000\u0014psr\u0000\u001bcom.sun.msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0"
+"\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0019L\u0000\fnamespaceURIq\u0000~\u0000\u0019xpq\u0000~\u0000\u001dq\u0000~\u0000\u001csr\u0000\u001dcom."
+"sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsr\u0000 com.sun.msv."
+"grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\u000bx"
+"q\u0000~\u0000\u0003q\u0000~\u0000\u0014psq\u0000~\u0000\u000fppsr\u0000\"com.sun.msv.datatype.xsd.QnameType\u0000\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0016q\u0000~\u0000\u001ct\u0000\u0005QNamesr\u00005com.sun.msv.datatype.xsd.Whit"
+"eSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u001fq\u0000~\u0000\"sq\u0000~\u0000#q\u0000~\u0000,q\u0000~"
+"\u0000\u001csr\u0000#com.sun.msv.grammar.SimpleNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocal"
+"Nameq\u0000~\u0000\u0019L\u0000\fnamespaceURIq\u0000~\u0000\u0019xr\u0000\u001dcom.sun.msv.grammar.NameCla"
+"ss\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000)http://www.w3.org/2001/XMLSchema-in"
+"stancesr\u00000com.sun.msv.grammar.Expression$EpsilonExpression\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003sq\u0000~\u0000\u0013\u0001psq\u0000~\u00000t\u0000\u0002idt\u0000\u0000sq\u0000~\u0000\npp\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\u000f"
+"ppsr\u0000$com.sun.msv.datatype.xsd.BooleanType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0016q"
+"\u0000~\u0000\u001ct\u0000\u0007booleanq\u0000~\u0000.q\u0000~\u0000\"sq\u0000~\u0000#q\u0000~\u0000@q\u0000~\u0000\u001csq\u0000~\u0000%ppsq\u0000~\u0000\'q\u0000~\u0000\u0014p"
+"q\u0000~\u0000)q\u0000~\u00002q\u0000~\u00006sq\u0000~\u00000t\u0000\fis-promotionq\u0000~\u0000:sq\u0000~\u0000%ppsq\u0000~\u0000\nq\u0000~\u0000\u0014"
+"p\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\npp\u0000sq\u0000~\u0000%ppsr\u0000 com.sun.msv.grammar.OneOrMore"
+"Exp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.msv.grammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003"
+"expq\u0000~\u0000\u0002xq\u0000~\u0000\u0003q\u0000~\u0000\u0014psq\u0000~\u0000\'q\u0000~\u0000\u0014psr\u00002com.sun.msv.grammar.Expr"
+"ession$AnyStringExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003q\u0000~\u00007psr\u0000 com.sun"
+".msv.grammar.AnyNameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u00001q\u0000~\u00006sq\u0000~\u00000t\u0000Ecom."
+"vodafone.global.er.decoupling.binding.response.RangeValueFul"
+"lTypet\u0000+http://java.sun.com/jaxb/xjc/dummy-elementssq\u0000~\u0000%pps"
+"q\u0000~\u0000\'q\u0000~\u0000\u0014pq\u0000~\u0000)q\u0000~\u00002q\u0000~\u00006sq\u0000~\u00000t\u0000\u000emonths-of-yearq\u0000~\u0000:q\u0000~\u00006s"
+"q\u0000~\u0000%ppsq\u0000~\u0000\nq\u0000~\u0000\u0014p\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\npp\u0000sq\u0000~\u0000%ppsq\u0000~\u0000Kq\u0000~\u0000\u0014psq\u0000"
+"~\u0000\'q\u0000~\u0000\u0014pq\u0000~\u0000Pq\u0000~\u0000Rq\u0000~\u00006sq\u0000~\u00000q\u0000~\u0000Tq\u0000~\u0000Usq\u0000~\u0000%ppsq\u0000~\u0000\'q\u0000~\u0000\u0014p"
+"q\u0000~\u0000)q\u0000~\u00002q\u0000~\u00006sq\u0000~\u00000t\u0000\rdays-of-monthq\u0000~\u0000:q\u0000~\u00006sq\u0000~\u0000%ppsq\u0000~\u0000"
+"\nq\u0000~\u0000\u0014p\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\npp\u0000sq\u0000~\u0000%ppsq\u0000~\u0000Kq\u0000~\u0000\u0014psq\u0000~\u0000\'q\u0000~\u0000\u0014pq\u0000~"
+"\u0000Pq\u0000~\u0000Rq\u0000~\u00006sq\u0000~\u00000q\u0000~\u0000Tq\u0000~\u0000Usq\u0000~\u0000%ppsq\u0000~\u0000\'q\u0000~\u0000\u0014pq\u0000~\u0000)q\u0000~\u00002q\u0000"
+"~\u00006sq\u0000~\u00000t\u0000\fdays-of-weekq\u0000~\u0000:q\u0000~\u00006sq\u0000~\u0000%ppsq\u0000~\u0000\nq\u0000~\u0000\u0014p\u0000sq\u0000~\u0000"
+"\u0000ppsq\u0000~\u0000\npp\u0000sq\u0000~\u0000%ppsq\u0000~\u0000Kq\u0000~\u0000\u0014psq\u0000~\u0000\'q\u0000~\u0000\u0014pq\u0000~\u0000Pq\u0000~\u0000Rq\u0000~\u00006s"
+"q\u0000~\u00000t\u0000Ccom.vodafone.global.er.decoupling.binding.response.D"
+"ayRangeFullTypeq\u0000~\u0000Usq\u0000~\u0000%ppsq\u0000~\u0000\'q\u0000~\u0000\u0014pq\u0000~\u0000)q\u0000~\u00002q\u0000~\u00006sq\u0000~\u0000"
+"0t\u0000\fhours-of-dayq\u0000~\u0000:q\u0000~\u00006sr\u0000\"com.sun.msv.grammar.Expression"
+"Pool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/Expression"
+"Pool$ClosedHash;xpsr\u0000-com.sun.msv.grammar.ExpressionPool$Clo"
+"sedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006parentt\u0000$Lcom/s"
+"un/msv/grammar/ExpressionPool;xp\u0000\u0000\u0000\u001d\u0001pq\u0000~\u0000kq\u0000~\u0000wq\u0000~\u0000Fq\u0000~\u0000Zq\u0000"
+"~\u0000fq\u0000~\u0000rq\u0000~\u0000Hq\u0000~\u0000\u000eq\u0000~\u0000\\q\u0000~\u0000hq\u0000~\u0000tq\u0000~\u0000Jq\u0000~\u0000^q\u0000~\u0000jq\u0000~\u0000vq\u0000~\u0000\u0006q\u0000"
+"~\u0000&q\u0000~\u0000Bq\u0000~\u0000Vq\u0000~\u0000bq\u0000~\u0000nq\u0000~\u0000{q\u0000~\u0000\u0005q\u0000~\u0000\tq\u0000~\u0000\u0007q\u0000~\u0000\bq\u0000~\u0000Mq\u0000~\u0000_q\u0000"
+"~\u0000<x"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public class Unmarshaller
        extends com.vodafone.global.er.decoupling.binding.response.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallingContext context) {
            super(context, "-------------------");
        }

        protected Unmarshaller(com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return com.vodafone.global.er.decoupling.binding.response.impl.DateTimeTierFullTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  9 :
                        if (("days-of-month" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 10;
                            return ;
                        }
                        state = 12;
                        continue outer;
                    case  13 :
                        if (("value" == ___local)&&("" == ___uri)) {
                            _DaysOfWeek = ((com.vodafone.global.er.decoupling.binding.response.impl.RangeValueFullTypeImpl) spawnChildFromEnterElement((com.vodafone.global.er.decoupling.binding.response.impl.RangeValueFullTypeImpl.class), 14, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        break;
                    case  12 :
                        if (("days-of-week" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 13;
                            return ;
                        }
                        state = 15;
                        continue outer;
                    case  10 :
                        if (("value" == ___local)&&("" == ___uri)) {
                            _DaysOfMonth = ((com.vodafone.global.er.decoupling.binding.response.impl.RangeValueFullTypeImpl) spawnChildFromEnterElement((com.vodafone.global.er.decoupling.binding.response.impl.RangeValueFullTypeImpl.class), 11, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        break;
                    case  16 :
                        if (("start-time" == ___local)&&("" == ___uri)) {
                            _HoursOfDay = ((com.vodafone.global.er.decoupling.binding.response.impl.DayRangeFullTypeImpl) spawnChildFromEnterElement((com.vodafone.global.er.decoupling.binding.response.impl.DayRangeFullTypeImpl.class), 17, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        break;
                    case  0 :
                        if (("id" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 1;
                            return ;
                        }
                        break;
                    case  18 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  6 :
                        if (("months-of-year" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 7;
                            return ;
                        }
                        state = 9;
                        continue outer;
                    case  15 :
                        if (("hours-of-day" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 16;
                            return ;
                        }
                        state = 18;
                        continue outer;
                    case  3 :
                        if (("is-promotion" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 4;
                            return ;
                        }
                        break;
                    case  7 :
                        if (("value" == ___local)&&("" == ___uri)) {
                            _MonthsOfYear = ((com.vodafone.global.er.decoupling.binding.response.impl.RangeValueFullTypeImpl) spawnChildFromEnterElement((com.vodafone.global.er.decoupling.binding.response.impl.RangeValueFullTypeImpl.class), 8, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        break;
                }
                super.enterElement(___uri, ___local, ___qname, __atts);
                break;
            }
        }

        public void leaveElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  14 :
                        if (("days-of-week" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 15;
                            return ;
                        }
                        break;
                    case  17 :
                        if (("hours-of-day" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 18;
                            return ;
                        }
                        break;
                    case  2 :
                        if (("id" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  9 :
                        state = 12;
                        continue outer;
                    case  12 :
                        state = 15;
                        continue outer;
                    case  5 :
                        if (("is-promotion" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 6;
                            return ;
                        }
                        break;
                    case  11 :
                        if (("days-of-month" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 12;
                            return ;
                        }
                        break;
                    case  18 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  6 :
                        state = 9;
                        continue outer;
                    case  15 :
                        state = 18;
                        continue outer;
                    case  8 :
                        if (("months-of-year" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 9;
                            return ;
                        }
                        break;
                }
                super.leaveElement(___uri, ___local, ___qname);
                break;
            }
        }

        public void enterAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  9 :
                        state = 12;
                        continue outer;
                    case  12 :
                        state = 15;
                        continue outer;
                    case  18 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  6 :
                        state = 9;
                        continue outer;
                    case  15 :
                        state = 18;
                        continue outer;
                }
                super.enterAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void leaveAttribute(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  9 :
                        state = 12;
                        continue outer;
                    case  12 :
                        state = 15;
                        continue outer;
                    case  18 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  6 :
                        state = 9;
                        continue outer;
                    case  15 :
                        state = 18;
                        continue outer;
                }
                super.leaveAttribute(___uri, ___local, ___qname);
                break;
            }
        }

        public void handleText(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                try {
                    switch (state) {
                        case  4 :
                            state = 5;
                            eatText1(value);
                            return ;
                        case  9 :
                            state = 12;
                            continue outer;
                        case  12 :
                            state = 15;
                            continue outer;
                        case  1 :
                            state = 2;
                            eatText2(value);
                            return ;
                        case  18 :
                            revertToParentFromText(value);
                            return ;
                        case  6 :
                            state = 9;
                            continue outer;
                        case  15 :
                            state = 18;
                            continue outer;
                    }
                } catch (java.lang.RuntimeException e) {
                    handleUnexpectedTextException(value, e);
                }
                break;
            }
        }

        private void eatText1(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _IsPromotion = javax.xml.bind.DatatypeConverter.parseBoolean(com.sun.xml.bind.WhiteSpaceProcessor.collapse(value));
                has_IsPromotion = true;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

        private void eatText2(final java.lang.String value)
            throws org.xml.sax.SAXException
        {
            try {
                _Id = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

    }

}
