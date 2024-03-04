package io.collective.basic;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class Blockchain {

    private List<Block> blocks;

    public Blockchain() {
        this.blocks = new ArrayList<>();
    }

    public boolean isEmpty() {
        return blocks.isEmpty();
    }

    public void add(Block block) {
        blocks.add(block);
    }

    public int size() {
        return blocks.size();
    }

    public boolean isValid() throws NoSuchAlgorithmException {

        // todo - check mined
        for (Block block : blocks) {
            if (!isMined(block)) {
                return false;
            }
        }
        
        // todo - check previous hash matches
        for (int i = 1; i < blocks.size(); i++) {
            Block currentBlock = blocks.get(i);
            Block previousBlock = blocks.get(i-1);
            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())){
                return false;
            }
        }

        // todo - check hash is correctly calculated
        for (Block block : blocks) {
            if (!block.getHash().equals(block.calculatedHash())) {
                return false;
            }
        }

        return true;
        
    }

    /// Supporting functions that you'll need.

    public static Block mine(Block block) throws NoSuchAlgorithmException {
        Block mined = new Block(block.getPreviousHash(), block.getTimestamp(), block.getNonce());

        while (!isMined(mined)) {
            mined = new Block(mined.getPreviousHash(), mined.getTimestamp(), mined.getNonce() + 1);
        }
        return mined;
    }

    public static boolean isMined(Block minedBlock) {
        return minedBlock.getHash().startsWith("00");
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }
}