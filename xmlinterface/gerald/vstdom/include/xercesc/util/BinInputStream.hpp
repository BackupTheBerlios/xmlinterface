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
 * $Log: BinInputStream.hpp,v $
 * Revision 1.1  2004/05/26 18:35:17  geraschna
 * vstdom Projekt, zum parsieren der xml files und senden an das GUI Toolkit
 *
 * Revision 1.4  2003/05/15 19:04:35  knoaman
 * Partial implementation of the configurable memory manager.
 *
 * Revision 1.3  2003/03/07 18:11:54  tng
 * Return a reference instead of void for operator=
 *
 * Revision 1.2  2002/11/04 15:22:03  tng
 * C++ Namespace Support.
 *
 * Revision 1.1.1.1  2002/02/01 22:22:10  peiyongz
 * sane_include
 *
 * Revision 1.4  2000/03/02 19:54:38  roddey
 * This checkin includes many changes done while waiting for the
 * 1.1.0 code to be finished. I can't list them all here, but a list is
 * available elsewhere.
 *
 * Revision 1.3  2000/02/24 20:05:23  abagchi
 * Swat for removing Log from API docs
 *
 * Revision 1.2  2000/02/06 07:48:01  rahulj
 * Year 2K copyright swat.
 *
 * Revision 1.1.1.1  1999/11/09 01:04:03  twl
 * Initial checkin
 *
 * Revision 1.3  1999/11/08 20:45:04  rahul
 * Swat for adding in Product name and CVS comment log variable.
 *
 */

#if !defined(BININPUTSTREAM_HPP)
#define BININPUTSTREAM_HPP

#include <xercesc/util/XMemory.hpp>

XERCES_CPP_NAMESPACE_BEGIN

class XMLUTIL_EXPORT BinInputStream : public XMemory
{
public :
    // -----------------------------------------------------------------------
    //  Virtual destructor for derived classes
    // -----------------------------------------------------------------------
    virtual ~BinInputStream();


    // -----------------------------------------------------------------------
    //  The virtual input stream interface
    // -----------------------------------------------------------------------
    virtual unsigned int curPos() const = 0;

    virtual unsigned int readBytes
    (
                XMLByte* const      toFill
        , const unsigned int        maxToRead
    ) = 0;


protected :
    // -----------------------------------------------------------------------
    //  Hidden Constructors
    // -----------------------------------------------------------------------
    BinInputStream();


private :
    // -----------------------------------------------------------------------
    //  Unimplemented Constructors
    // -----------------------------------------------------------------------
    BinInputStream(const BinInputStream&);
    BinInputStream& operator=(const BinInputStream&);
};

XERCES_CPP_NAMESPACE_END

#endif
