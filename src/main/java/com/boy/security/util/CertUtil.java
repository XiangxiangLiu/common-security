package com.boy.security.util;

import java.io.ByteArrayInputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class CertUtil {

    public static X509Certificate getX509Cert(String certB64) throws CertificateException {
        ByteArrayInputStream in = new ByteArrayInputStream(Base64Util.decode(certB64));
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(in);
        return certificate;
    }

    public static void main (String[] args) throws CertificateException {
        String certB64 = "MIIEIDCCAwigAwIBAgIUCDGaD7UdGXDV1S3pj2quFOaRX0gwDQYJKoZIhvcNAQEFBQAwZDEeMBwGA1UEAwwVV2FuZ1lpbiBVc2VyIENBKFRlc3QpMR8wHQYDVQQLDBZXYW5nWWluIFNlY3VyaXR5Q2VudGVyMRQwEgYDVQQKDAtXYW5nWWluLmNvbTELMAkGA1UEBhMCQ04wHhcNMTcwMTA5MDYxMTQwWhcNMTgwMTA5MDYxMTQwWjBgMQswCQYDVQQLDAJqcjELMAkGA1UECgwCamQxJDAiBgkqhkiG9w0BCQEWFXd5d2FuZ3RpZWNoZW5nQGpkLmNvbTEeMBwGA1UEAwwVYWtzX3Rlc3QoQUtTMDAwMDBBS1MpMIIBIDANBgkqhkiG9w0BAQEFAAOCAQ0AMIIBCAKCAQEA4F/CtacqXkIKMTIeHxV790KoagSZ2itL5YzjmdrD345iGI3Y1P/eMcjnqkVfQ5+TP7IBlcdubMkx6Kdlgr5u8zpTPEbJR2kcuNhrbhHRg9uIpkc4+6hYBBOQrwgYohgQSKp8mapYT0I4diaxEpIECApZVCWdDGB1rk8PHkSwBcztiGgBTAY7oC3ifWNRgq2aRNDE1ZMEAG2StIqBx1SuB3Hoowwu6h4D8kfM3LiQ4jAokAJKAAfLt4O27FKO2fGeGj4GepZ1REVUcik3AeHnvef5UxqQAjEKKzNycMJPXkxDZM0rdbVeBi97JRDkJeacIPZzxtvGH66l3pKGWIBitQIBA6OBzzCBzDAJBgNVHRMEAjAAMAsGA1UdDwQEAwIE8DByBgNVHR8EazBpMGegZaBjhmFGVUxMTkFNRVM6VVJJOmh0dHA6Ly8xNzIuMjQuNi4zNDo4MDgwL3B1YmxpYy9pdHJ1c2NybD9DQT02QTU2RUQyRkZCQkM3QTk5MTU5MkEzN0MzMjFFOTU1QUMwNzM0OTc0MB8GA1UdIwQYMBaAFH/r/GfKS6oxTAbxVq4asoqL3zygMB0GA1UdDgQWBBQr6nneEvO3b4AiTDmaPY13v+ne+TANBgkqhkiG9w0BAQUFAAOCAQEAWFtj1p56biFj5JH2N3xMXAxCQZAApWHwpWygxwxUqdaIVqrxqp6Ic6XDiXR6sDqiAea9HUmNyB5hOWJFQDL3M0X/5YKY7KPOhiAeV4voGYSqg2H2T6/J9zYkOu4LNEBy2NECT38RPdVNoleJ9ZLIL5rYfO8nTN3UO59I5q26EUcFkYiNW4J66v3DEDSNKRISAatGQR2rkbFWdgGlDfsvJEn8c/pENLXkiScHxMQUpZepG7xnGVKMDjeCM8IU7hXMiMU3Lb2xlondjosR/vaeGwyJAIxAKLeTyTjAJu6ywIQ0+84OlSpmwjhGscPIKIH26JwqAaV0Ooo9pxJpslTu1Q==";
        X509Certificate x509Cert = getX509Cert(certB64);
        byte[] snBytes = x509Cert.getSerialNumber().toByteArray();
        String hex = Hex.toHex(snBytes).toUpperCase();
        System.out.println(hex);
    }


}
