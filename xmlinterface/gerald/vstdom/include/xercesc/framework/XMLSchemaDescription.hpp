/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Xerces" and "Apache Software Foundation" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact apache\@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache",
 *    nor may "Apache" appear in their name, without prior written
 *    permission of the Apache Software Foundation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation, and was
 * originally based on software copyright (c) 1999, International
 * Business Machines, Inc., http://www.ibm.com .  For more information
 * on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */

/*
 * $Log: XMLSchemaDescription.hpp,v $
 * Revision 1.3  2004/06/03 20:45:20  geraschna
 * alle Änderungen zum 3.6. übernommen
 *
 * Revision 1.3  2003/10/14 15:17:47  peiyongz
 * Implementation of Serialization/Deserialization
 *
 * Revision 1.2  2003/07/31 17:03:19  peiyongz
 * locationHint incrementally added
 *
 * Revision 1.1  2003/06/20 18:37:39  peiyongz
 * Stateless Grammar Pool :: Part I
 *
 * $Id: XMLSchemaDescription.hpp,v 1.3 2004/06/03 20:45:20 geraschna Exp $
 *
 */

#if !defined(XMLSCHEMADESCRIPTION_HPP)
#define XMLSCHEMADESCRIPTION_HPP

#include <xercesc/framework/XMLGrammarDescription.hpp>
#include <xercesc/util/RefArrayVectorOf.hpp>

XERCES_CPP_NAMESPACE_BEGIN

typedef const XMLCh* const LocationHint;

class XMLPARSER_EXPORT XMLSchemaDescription : public XMLGrammarDescription
{
public :
    // -----------------------------------------------------------------------
    /** @name Virtual destructor for derived classes */
    // -----------------------------------------------------------------------
    //@{
    /**
      * virtual destructor
      *
      */
    virtual ~XMLSchemaDescription();
    //@}

    // -----------------------------------------------------------------------
    /** @name Implementation of Grammar Description Interface */
    // -----------------------------------------------------------------------
    //@{     
    /**
      * getGrammarType
      *
      */
    virtual Grammar::GrammarType   getGrammarType() const
    {
        return Grammar::SchemaGrammarType;
    }
    //@}

    // -----------------------------------------------------------------------
    /** @name The SchemaDescription Interface */
    // -----------------------------------------------------------------------
    //@{

    enum ContextType 
         {
            CONTEXT_INCLUDE,
            CONTEXT_REDEFINE,
            CONTEXT_IMPORT,
            CONTEXT_PREPARSE,
            CONTEXT_INSTANCE,
            CONTEXT_ELEMENT,
            CONTEXT_ATTRIBUTE,
            CONTEXT_XSITYPE,
            CONTEXT_UNKNOWN
         };

    /**
      * getContextType
      *
      */	
    virtual ContextType                getContextType() const = 0;

    /**
      * getTargetNamespace
      *
      */	
    virtual const XMLCh*               getTargetNamespace() const = 0;

    /**
      * getLocationHints
      *
      */	
    virtual RefArrayVectorOf<XMLCh>*   getLocationHints() const = 0;

    /**
      * getTriggeringComponent
      *
      */	
    virtual const QName*               getTriggeringComponent() const = 0;

    /**
      * getenclosingElementName
      *
      */	
    virtual const QName*               getEnclosingElementName() const = 0;

    /**
      * getAttributes
      *
      */	
    virtual const XMLAttDef*           getAttributes() const = 0;

    /**
      * setContextType
      *
      */	
    virtual void                       setContextType(ContextType) = 0;

    /**
      * setTargetNamespace
      *
      */	
    virtual void                       setTargetNamespace(const XMLCh* const) = 0;

    /**
      * setLocationHints
      *
      */	
    virtual void                       setLocationHints(const XMLCh* const) = 0;

    /**
      * setTriggeringComponent
      *
      */	
    virtual void                       setTriggeringComponent(QName* const) = 0;

    /**
      * getenclosingElementName
      *
      */	
    virtual void                       setEnclosingElementName(QName* const) = 0;

    /**
      * setAttributes
      *
      */	
    virtual void                       setAttributes(XMLAttDef* const) = 0;
    //@}	          
	          
    /***
     * Support for Serialization/De-serialization
     ***/
    DECL_XSERIALIZABLE(XMLSchemaDescription)

protected :
    // -----------------------------------------------------------------------
    /**  Hidden Constructors */
    // -----------------------------------------------------------------------
    //@{
    XMLSchemaDescription(MemoryManager* const memMgr = XMLPlatformUtils::fgMemoryManager);
    //@}

private :
    // -----------------------------------------------------------------------
    /** name  Unimplemented copy constructor and operator= */
    // -----------------------------------------------------------------------
    //@{
    XMLSchemaDescription(const XMLSchemaDescription& );
    XMLSchemaDescription& operator=(const XMLSchemaDescription& );
    //@}

};


XERCES_CPP_NAMESPACE_END

#endif
