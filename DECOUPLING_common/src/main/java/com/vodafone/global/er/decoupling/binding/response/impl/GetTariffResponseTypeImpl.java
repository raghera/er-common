//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v1.0.5-b16-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.08.13 at 04:57:40 PM BST 
//


package com.vodafone.global.er.decoupling.binding.response.impl;

public class GetTariffResponseTypeImpl implements com.vodafone.global.er.decoupling.binding.response.GetTariffResponseType, com.sun.xml.bind.JAXBObject, com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallableObject, com.vodafone.global.er.decoupling.binding.response.impl.runtime.XMLSerializable, com.vodafone.global.er.decoupling.binding.response.impl.runtime.ValidatableObject
{

    protected com.vodafone.global.er.decoupling.binding.response.GetTariffResponseType.PackageIdsType _PackageIds;
    protected java.lang.String _TariffName;
    public final static java.lang.Class version = (com.vodafone.global.er.decoupling.binding.response.impl.JAXBVersion.class);
    private static com.sun.msv.grammar.Grammar schemaFragment;

    private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
        return (com.vodafone.global.er.decoupling.binding.response.GetTariffResponseType.class);
    }

    public com.vodafone.global.er.decoupling.binding.response.GetTariffResponseType.PackageIdsType getPackageIds() {
        return _PackageIds;
    }

    public void setPackageIds(com.vodafone.global.er.decoupling.binding.response.GetTariffResponseType.PackageIdsType value) {
        _PackageIds = value;
    }

    public java.lang.String getTariffName() {
        return _TariffName;
    }

    public void setTariffName(java.lang.String value) {
        _TariffName = value;
    }

    public com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallingContext context) {
        return new com.vodafone.global.er.decoupling.binding.response.impl.GetTariffResponseTypeImpl.Unmarshaller(context);
    }

    public void serializeBody(com.vodafone.global.er.decoupling.binding.response.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
        context.startElement("", "tariff-name");
        context.endNamespaceDecls();
        context.endAttributes();
        try {
            context.text(((java.lang.String) _TariffName), "TariffName");
        } catch (java.lang.Exception e) {
            com.vodafone.global.er.decoupling.binding.response.impl.runtime.Util.handlePrintConversionException(this, e, context);
        }
        context.endElement();
        if (_PackageIds!= null) {
            context.startElement("", "package-ids");
            context.childAsURIs(((com.sun.xml.bind.JAXBObject) _PackageIds), "PackageIds");
            context.endNamespaceDecls();
            context.childAsAttributes(((com.sun.xml.bind.JAXBObject) _PackageIds), "PackageIds");
            context.endAttributes();
            context.childAsBody(((com.sun.xml.bind.JAXBObject) _PackageIds), "PackageIds");
            context.endElement();
        }
    }

    public void serializeAttributes(com.vodafone.global.er.decoupling.binding.response.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
    }

    public void serializeURIs(com.vodafone.global.er.decoupling.binding.response.impl.runtime.XMLSerializer context)
        throws org.xml.sax.SAXException
    {
    }

    public java.lang.Class getPrimaryInterface() {
        return (com.vodafone.global.er.decoupling.binding.response.GetTariffResponseType.class);
    }

    public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
        if (schemaFragment == null) {
            schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.su"
+"n.msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gra"
+"mmar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expressi"
+"on\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000b"
+"expandedExpq\u0000~\u0000\u0002xpppsr\u0000\'com.sun.msv.grammar.trex.ElementPatt"
+"ern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLcom/sun/msv/grammar/NameClass;"
+"xr\u0000\u001ecom.sun.msv.grammar.ElementExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndecl"
+"aredAttributesL\u0000\fcontentModelq\u0000~\u0000\u0002xq\u0000~\u0000\u0003pp\u0000sq\u0000~\u0000\u0000ppsr\u0000\u001bcom.s"
+"un.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001fLorg/relaxng/dataty"
+"pe/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/sun/msv/util/String"
+"Pair;xq\u0000~\u0000\u0003sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000#c"
+"om.sun.msv.datatype.xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysVali"
+"dxr\u0000*com.sun.msv.datatype.xsd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr"
+"\u0000%com.sun.msv.datatype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.su"
+"n.msv.datatype.xsd.XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUrit"
+"\u0000\u0012Ljava/lang/String;L\u0000\btypeNameq\u0000~\u0000\u0015L\u0000\nwhiteSpacet\u0000.Lcom/sun"
+"/msv/datatype/xsd/WhiteSpaceProcessor;xpt\u0000 http://www.w3.org"
+"/2001/XMLSchemat\u0000\u0006stringsr\u00005com.sun.msv.datatype.xsd.WhiteSp"
+"aceProcessor$Preserve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd"
+".WhiteSpaceProcessor\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0001sr\u00000com.sun.msv.grammar.Ex"
+"pression$NullSetExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003q\u0000~\u0000\u0010psr\u0000\u001bcom.sun"
+".msv.util.StringPair\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0015L\u0000\fnamespace"
+"URIq\u0000~\u0000\u0015xpq\u0000~\u0000\u0019q\u0000~\u0000\u0018sr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0001ppsr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L"
+"\u0000\u0003expq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\u0007xq\u0000~\u0000\u0003q\u0000~\u0000\u0010psq\u0000~\u0000\u000bppsr\u0000\"com.sun.m"
+"sv.datatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0012q\u0000~\u0000\u0018t\u0000\u0005QNamesr\u00005c"
+"om.sun.msv.datatype.xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001"
+"\u0002\u0000\u0000xq\u0000~\u0000\u001bq\u0000~\u0000\u001esq\u0000~\u0000\u001fq\u0000~\u0000(q\u0000~\u0000\u0018sr\u0000#com.sun.msv.grammar.Simple"
+"NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0015L\u0000\fnamespaceURIq\u0000~\u0000\u0015xr\u0000"
+"\u001dcom.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000)http://"
+"www.w3.org/2001/XMLSchema-instancesr\u00000com.sun.msv.grammar.Ex"
+"pression$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003sq\u0000~\u0000\u000f\u0001psq\u0000~\u0000,t\u0000\u000b"
+"tariff-namet\u0000\u0000sq\u0000~\u0000!ppsq\u0000~\u0000\u0006q\u0000~\u0000\u0010p\u0000sq\u0000~\u0000\u0000ppsq\u0000~\u0000\u0006pp\u0000sq\u0000~\u0000!pp"
+"sr\u0000 com.sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.m"
+"sv.grammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0002xq\u0000~\u0000\u0003q\u0000~\u0000\u0010psq\u0000~\u0000#q"
+"\u0000~\u0000\u0010psr\u00002com.sun.msv.grammar.Expression$AnyStringExpression\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003q\u0000~\u00003psr\u0000 com.sun.msv.grammar.AnyNameClass\u0000\u0000"
+"\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000-q\u0000~\u00002sq\u0000~\u0000,t\u0000Wcom.vodafone.global.er.decoupli"
+"ng.binding.response.GetTariffResponseType.PackageIdsTypet\u0000+h"
+"ttp://java.sun.com/jaxb/xjc/dummy-elementssq\u0000~\u0000!ppsq\u0000~\u0000#q\u0000~\u0000"
+"\u0010pq\u0000~\u0000%q\u0000~\u0000.q\u0000~\u00002sq\u0000~\u0000,t\u0000\u000bpackage-idsq\u0000~\u00006q\u0000~\u00002sr\u0000\"com.sun.m"
+"sv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/m"
+"sv/grammar/ExpressionPool$ClosedHash;xpsr\u0000-com.sun.msv.gramm"
+"ar.ExpressionPool$ClosedHash\u00d7j\u00d0N\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstreamVers"
+"ionL\u0000\u0006parentt\u0000$Lcom/sun/msv/grammar/ExpressionPool;xp\u0000\u0000\u0000\b\u0001pq"
+"\u0000~\u00007q\u0000~\u00009q\u0000~\u0000\nq\u0000~\u0000;q\u0000~\u0000\"q\u0000~\u0000Gq\u0000~\u0000\u0005q\u0000~\u0000>x"));
        }
        return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
    }

    public static class PackageIdsTypeImpl implements com.vodafone.global.er.decoupling.binding.response.GetTariffResponseType.PackageIdsType, com.sun.xml.bind.JAXBObject, com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallableObject, com.vodafone.global.er.decoupling.binding.response.impl.runtime.XMLSerializable, com.vodafone.global.er.decoupling.binding.response.impl.runtime.ValidatableObject
    {

        protected com.sun.xml.bind.util.ListImpl _PackageId;
        public final static java.lang.Class version = (com.vodafone.global.er.decoupling.binding.response.impl.JAXBVersion.class);
        private static com.sun.msv.grammar.Grammar schemaFragment;

        private final static java.lang.Class PRIMARY_INTERFACE_CLASS() {
            return (com.vodafone.global.er.decoupling.binding.response.GetTariffResponseType.PackageIdsType.class);
        }

        protected com.sun.xml.bind.util.ListImpl _getPackageId() {
            if (_PackageId == null) {
                _PackageId = new com.sun.xml.bind.util.ListImpl(new java.util.ArrayList());
            }
            return _PackageId;
        }

        public java.util.List getPackageId() {
            return _getPackageId();
        }

        public com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallingEventHandler createUnmarshaller(com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallingContext context) {
            return new com.vodafone.global.er.decoupling.binding.response.impl.GetTariffResponseTypeImpl.PackageIdsTypeImpl.Unmarshaller(context);
        }

        public void serializeBody(com.vodafone.global.er.decoupling.binding.response.impl.runtime.XMLSerializer context)
            throws org.xml.sax.SAXException
        {
            int idx1 = 0;
            final int len1 = ((_PackageId == null)? 0 :_PackageId.size());
            while (idx1 != len1) {
                context.startElement("", "package-id");
                int idx_0 = idx1;
                try {
                    idx_0 += 1;
                } catch (java.lang.Exception e) {
                    com.vodafone.global.er.decoupling.binding.response.impl.runtime.Util.handlePrintConversionException(this, e, context);
                }
                context.endNamespaceDecls();
                int idx_1 = idx1;
                try {
                    idx_1 += 1;
                } catch (java.lang.Exception e) {
                    com.vodafone.global.er.decoupling.binding.response.impl.runtime.Util.handlePrintConversionException(this, e, context);
                }
                context.endAttributes();
                try {
                    context.text(((java.lang.String) _PackageId.get(idx1 ++)), "PackageId");
                } catch (java.lang.Exception e) {
                    com.vodafone.global.er.decoupling.binding.response.impl.runtime.Util.handlePrintConversionException(this, e, context);
                }
                context.endElement();
            }
        }

        public void serializeAttributes(com.vodafone.global.er.decoupling.binding.response.impl.runtime.XMLSerializer context)
            throws org.xml.sax.SAXException
        {
            int idx1 = 0;
            final int len1 = ((_PackageId == null)? 0 :_PackageId.size());
            while (idx1 != len1) {
                try {
                    idx1 += 1;
                } catch (java.lang.Exception e) {
                    com.vodafone.global.er.decoupling.binding.response.impl.runtime.Util.handlePrintConversionException(this, e, context);
                }
            }
        }

        public void serializeURIs(com.vodafone.global.er.decoupling.binding.response.impl.runtime.XMLSerializer context)
            throws org.xml.sax.SAXException
        {
            int idx1 = 0;
            final int len1 = ((_PackageId == null)? 0 :_PackageId.size());
            while (idx1 != len1) {
                try {
                    idx1 += 1;
                } catch (java.lang.Exception e) {
                    com.vodafone.global.er.decoupling.binding.response.impl.runtime.Util.handlePrintConversionException(this, e, context);
                }
            }
        }

        public java.lang.Class getPrimaryInterface() {
            return (com.vodafone.global.er.decoupling.binding.response.GetTariffResponseType.PackageIdsType.class);
        }

        public com.sun.msv.verifier.DocumentDeclaration createRawValidator() {
            if (schemaFragment == null) {
                schemaFragment = com.sun.xml.bind.validator.SchemaDeserializer.deserialize((
 "\u00ac\u00ed\u0000\u0005sr\u0000\u001dcom.sun.msv.grammar.ChoiceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\u001dcom.sun."
+"msv.grammar.BinaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0004exp1t\u0000 Lcom/sun/msv/gramm"
+"ar/Expression;L\u0000\u0004exp2q\u0000~\u0000\u0002xr\u0000\u001ecom.sun.msv.grammar.Expression"
+"\u00f8\u0018\u0082\u00e8N5~O\u0002\u0000\u0002L\u0000\u0013epsilonReducibilityt\u0000\u0013Ljava/lang/Boolean;L\u0000\u000bex"
+"pandedExpq\u0000~\u0000\u0002xpppsr\u0000 com.sun.msv.grammar.OneOrMoreExp\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0000\u0001\u0002\u0000\u0000xr\u0000\u001ccom.sun.msv.grammar.UnaryExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\u0003expq\u0000~\u0000\u0002x"
+"q\u0000~\u0000\u0003sr\u0000\u0011java.lang.Boolean\u00cd r\u0080\u00d5\u009c\u00fa\u00ee\u0002\u0000\u0001Z\u0000\u0005valuexp\u0000psr\u0000\'com.sun"
+".msv.grammar.trex.ElementPattern\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001L\u0000\tnameClasst\u0000\u001fLc"
+"om/sun/msv/grammar/NameClass;xr\u0000\u001ecom.sun.msv.grammar.Element"
+"Exp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002Z\u0000\u001aignoreUndeclaredAttributesL\u0000\fcontentModelq\u0000"
+"~\u0000\u0002xq\u0000~\u0000\u0003q\u0000~\u0000\np\u0000sr\u0000\u001fcom.sun.msv.grammar.SequenceExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002"
+"\u0000\u0000xq\u0000~\u0000\u0001ppsr\u0000\u001bcom.sun.msv.grammar.DataExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\u0002dtt\u0000\u001f"
+"Lorg/relaxng/datatype/Datatype;L\u0000\u0006exceptq\u0000~\u0000\u0002L\u0000\u0004namet\u0000\u001dLcom/"
+"sun/msv/util/StringPair;xq\u0000~\u0000\u0003q\u0000~\u0000\npsr\u0000#com.sun.msv.datatype"
+".xsd.StringType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0001Z\u0000\risAlwaysValidxr\u0000*com.sun.msv.da"
+"tatype.xsd.BuiltinAtomicType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000%com.sun.msv.datat"
+"ype.xsd.ConcreteType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000\'com.sun.msv.datatype.xsd."
+"XSDatatypeImpl\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0003L\u0000\fnamespaceUrit\u0000\u0012Ljava/lang/String"
+";L\u0000\btypeNameq\u0000~\u0000\u0019L\u0000\nwhiteSpacet\u0000.Lcom/sun/msv/datatype/xsd/W"
+"hiteSpaceProcessor;xpt\u0000 http://www.w3.org/2001/XMLSchemat\u0000\u0006s"
+"tringsr\u00005com.sun.msv.datatype.xsd.WhiteSpaceProcessor$Preser"
+"ve\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xr\u0000,com.sun.msv.datatype.xsd.WhiteSpaceProcesso"
+"r\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xp\u0001sr\u00000com.sun.msv.grammar.Expression$NullSetExp"
+"ression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003q\u0000~\u0000\npsr\u0000\u001bcom.sun.msv.util.StringPai"
+"r\u00d0t\u001ejB\u008f\u008d\u00a0\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0019L\u0000\fnamespaceURIq\u0000~\u0000\u0019xpq\u0000~\u0000\u001dq\u0000~\u0000"
+"\u001csq\u0000~\u0000\u0000ppsr\u0000 com.sun.msv.grammar.AttributeExp\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\u0003e"
+"xpq\u0000~\u0000\u0002L\u0000\tnameClassq\u0000~\u0000\fxq\u0000~\u0000\u0003q\u0000~\u0000\npsq\u0000~\u0000\u0011ppsr\u0000\"com.sun.msv."
+"datatype.xsd.QnameType\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0016q\u0000~\u0000\u001ct\u0000\u0005QNamesr\u00005com."
+"sun.msv.datatype.xsd.WhiteSpaceProcessor$Collapse\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000"
+"xq\u0000~\u0000\u001fq\u0000~\u0000\"sq\u0000~\u0000#q\u0000~\u0000+q\u0000~\u0000\u001csr\u0000#com.sun.msv.grammar.SimpleNam"
+"eClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0002L\u0000\tlocalNameq\u0000~\u0000\u0019L\u0000\fnamespaceURIq\u0000~\u0000\u0019xr\u0000\u001dco"
+"m.sun.msv.grammar.NameClass\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xpt\u0000\u0004typet\u0000)http://www"
+".w3.org/2001/XMLSchema-instancesr\u00000com.sun.msv.grammar.Expre"
+"ssion$EpsilonExpression\u0000\u0000\u0000\u0000\u0000\u0000\u0000\u0001\u0002\u0000\u0000xq\u0000~\u0000\u0003sq\u0000~\u0000\t\u0001psq\u0000~\u0000/t\u0000\npac"
+"kage-idt\u0000\u0000q\u0000~\u00005sr\u0000\"com.sun.msv.grammar.ExpressionPool\u0000\u0000\u0000\u0000\u0000\u0000\u0000"
+"\u0001\u0002\u0000\u0001L\u0000\bexpTablet\u0000/Lcom/sun/msv/grammar/ExpressionPool$Closed"
+"Hash;xpsr\u0000-com.sun.msv.grammar.ExpressionPool$ClosedHash\u00d7j\u00d0N"
+"\u00ef\u00e8\u00ed\u001c\u0003\u0000\u0003I\u0000\u0005countB\u0000\rstreamVersionL\u0000\u0006parentt\u0000$Lcom/sun/msv/gram"
+"mar/ExpressionPool;xp\u0000\u0000\u0000\u0004\u0001pq\u0000~\u0000\u0010q\u0000~\u0000\u0005q\u0000~\u0000%q\u0000~\u0000\bx"));
            }
            return new com.sun.msv.verifier.regexp.REDocumentDeclaration(schemaFragment);
        }

        public class Unmarshaller
            extends com.vodafone.global.er.decoupling.binding.response.impl.runtime.AbstractUnmarshallingEventHandlerImpl
        {


            public Unmarshaller(com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallingContext context) {
                super(context, "----");
            }

            protected Unmarshaller(com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallingContext context, int startState) {
                this(context);
                state = startState;
            }

            public java.lang.Object owner() {
                return com.vodafone.global.er.decoupling.binding.response.impl.GetTariffResponseTypeImpl.PackageIdsTypeImpl.this;
            }

            public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
                throws org.xml.sax.SAXException
            {
                int attIdx;
                outer:
                while (true) {
                    switch (state) {
                        case  3 :
                            if (("package-id" == ___local)&&("" == ___uri)) {
                                context.pushAttributes(__atts, true);
                                state = 1;
                                return ;
                            }
                            revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                            return ;
                        case  0 :
                            if (("package-id" == ___local)&&("" == ___uri)) {
                                context.pushAttributes(__atts, true);
                                state = 1;
                                return ;
                            }
                            state = 3;
                            continue outer;
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
                        case  3 :
                            revertToParentFromLeaveElement(___uri, ___local, ___qname);
                            return ;
                        case  2 :
                            if (("package-id" == ___local)&&("" == ___uri)) {
                                context.popAttributes();
                                state = 3;
                                return ;
                            }
                            break;
                        case  0 :
                            state = 3;
                            continue outer;
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
                        case  3 :
                            revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                            return ;
                        case  0 :
                            state = 3;
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
                        case  3 :
                            revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                            return ;
                        case  0 :
                            state = 3;
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
                            case  3 :
                                revertToParentFromText(value);
                                return ;
                            case  1 :
                                state = 2;
                                eatText1(value);
                                return ;
                            case  0 :
                                state = 3;
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
                    _getPackageId().add(value);
                } catch (java.lang.Exception e) {
                    handleParseConversionException(e);
                }
            }

        }

    }

    public class Unmarshaller
        extends com.vodafone.global.er.decoupling.binding.response.impl.runtime.AbstractUnmarshallingEventHandlerImpl
    {


        public Unmarshaller(com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallingContext context) {
            super(context, "-------");
        }

        protected Unmarshaller(com.vodafone.global.er.decoupling.binding.response.impl.runtime.UnmarshallingContext context, int startState) {
            this(context);
            state = startState;
        }

        public java.lang.Object owner() {
            return com.vodafone.global.er.decoupling.binding.response.impl.GetTariffResponseTypeImpl.this;
        }

        public void enterElement(java.lang.String ___uri, java.lang.String ___local, java.lang.String ___qname, org.xml.sax.Attributes __atts)
            throws org.xml.sax.SAXException
        {
            int attIdx;
            outer:
            while (true) {
                switch (state) {
                    case  0 :
                        if (("tariff-name" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, true);
                            state = 1;
                            return ;
                        }
                        break;
                    case  3 :
                        if (("package-ids" == ___local)&&("" == ___uri)) {
                            context.pushAttributes(__atts, false);
                            state = 4;
                            return ;
                        }
                        state = 6;
                        continue outer;
                    case  6 :
                        revertToParentFromEnterElement(___uri, ___local, ___qname, __atts);
                        return ;
                    case  4 :
                        if (("package-id" == ___local)&&("" == ___uri)) {
                            _PackageIds = ((com.vodafone.global.er.decoupling.binding.response.impl.GetTariffResponseTypeImpl.PackageIdsTypeImpl) spawnChildFromEnterElement((com.vodafone.global.er.decoupling.binding.response.impl.GetTariffResponseTypeImpl.PackageIdsTypeImpl.class), 5, ___uri, ___local, ___qname, __atts));
                            return ;
                        }
                        _PackageIds = ((com.vodafone.global.er.decoupling.binding.response.impl.GetTariffResponseTypeImpl.PackageIdsTypeImpl) spawnChildFromEnterElement((com.vodafone.global.er.decoupling.binding.response.impl.GetTariffResponseTypeImpl.PackageIdsTypeImpl.class), 5, ___uri, ___local, ___qname, __atts));
                        return ;
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
                    case  3 :
                        state = 6;
                        continue outer;
                    case  6 :
                        revertToParentFromLeaveElement(___uri, ___local, ___qname);
                        return ;
                    case  4 :
                        _PackageIds = ((com.vodafone.global.er.decoupling.binding.response.impl.GetTariffResponseTypeImpl.PackageIdsTypeImpl) spawnChildFromLeaveElement((com.vodafone.global.er.decoupling.binding.response.impl.GetTariffResponseTypeImpl.PackageIdsTypeImpl.class), 5, ___uri, ___local, ___qname));
                        return ;
                    case  2 :
                        if (("tariff-name" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 3;
                            return ;
                        }
                        break;
                    case  5 :
                        if (("package-ids" == ___local)&&("" == ___uri)) {
                            context.popAttributes();
                            state = 6;
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
                    case  3 :
                        state = 6;
                        continue outer;
                    case  6 :
                        revertToParentFromEnterAttribute(___uri, ___local, ___qname);
                        return ;
                    case  4 :
                        _PackageIds = ((com.vodafone.global.er.decoupling.binding.response.impl.GetTariffResponseTypeImpl.PackageIdsTypeImpl) spawnChildFromEnterAttribute((com.vodafone.global.er.decoupling.binding.response.impl.GetTariffResponseTypeImpl.PackageIdsTypeImpl.class), 5, ___uri, ___local, ___qname));
                        return ;
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
                    case  3 :
                        state = 6;
                        continue outer;
                    case  6 :
                        revertToParentFromLeaveAttribute(___uri, ___local, ___qname);
                        return ;
                    case  4 :
                        _PackageIds = ((com.vodafone.global.er.decoupling.binding.response.impl.GetTariffResponseTypeImpl.PackageIdsTypeImpl) spawnChildFromLeaveAttribute((com.vodafone.global.er.decoupling.binding.response.impl.GetTariffResponseTypeImpl.PackageIdsTypeImpl.class), 5, ___uri, ___local, ___qname));
                        return ;
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
                        case  1 :
                            state = 2;
                            eatText1(value);
                            return ;
                        case  3 :
                            state = 6;
                            continue outer;
                        case  6 :
                            revertToParentFromText(value);
                            return ;
                        case  4 :
                            _PackageIds = ((com.vodafone.global.er.decoupling.binding.response.impl.GetTariffResponseTypeImpl.PackageIdsTypeImpl) spawnChildFromText((com.vodafone.global.er.decoupling.binding.response.impl.GetTariffResponseTypeImpl.PackageIdsTypeImpl.class), 5, value));
                            return ;
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
                _TariffName = value;
            } catch (java.lang.Exception e) {
                handleParseConversionException(e);
            }
        }

    }

}
