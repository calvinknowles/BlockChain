package io.collective.basic;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block {
    private final String previousHash;
    private final long timestamp;
    private final int nonce;
    private final String hash;

    public Block(String previousHash, long timestamp, int nonce) throws NoSuchAlgorithmException {
        this.previousHash = previousHash;
        this.timestamp = timestamp;
        this.nonce = nonce;
        this.hash = calculatedHash();
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getNonce() {
        return nonce;
    }

    @Override
    public String toString() {
        return "Block [previousHash=" + previousHash + ", timestamp=" + timestamp + ", nonce=" + nonce + ", hash="
                + hash + "]";
    }

    public String getHash() {
        return hash;
    }

    public String calculatedHash() throws NoSuchAlgorithmException {
        String data = previousHash + Long.toString(timestamp) + Integer.toString(nonce);
        return calculateHash(data);
    }

    /// Supporting functions that you'll need.

    static String calculateHash(String string) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(string.getBytes());
        return String.format("%064x", new BigInteger(1, digest.digest()));
    }
}