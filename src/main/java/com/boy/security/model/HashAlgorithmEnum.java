package com.boy.security.model;

public enum HashAlgorithmEnum {

    MD5("MD5", "md5"),
    SHA1("SHA1", "sha-1"),
    SHA256("SHA-256","sha-256");

    private String hashAlgName;
    private String desc;

    private HashAlgorithmEnum(String hashAlgName, String desc) {
        this.hashAlgName = hashAlgName;
        this.desc = desc;
    }

    public String getHashAlgName() {
        return hashAlgName;
    }

    public String getDesc() {
        return desc;
    }
}
