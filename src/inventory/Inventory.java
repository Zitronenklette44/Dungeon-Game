package inventory;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JLabel;

import action.Logger;
import game.GameLogic;
import loot.LootTableTemplate;
import loot.SpawnLoot;
import loot.items.EmptyItem;
import loot.items.ItemTemplate;

public class Inventory extends JLabel {
    private static final long serialVersionUID = 1L;
    private boolean craftingTab = false;
    public int maxInventarSlots;
    private static final int SLOT_SIZE = 60;
    private static final int SLOT_PADDING = 70;
    private static final int SLOT_ROWS = 8;
    private static final int SLOT_COLUMNS = 5;

    private int draggedSlot = -1;
    private ItemTemplate draggedItem = null;

    public Inventory(int maxInventarSlots) {
        this.maxInventarSlots = maxInventarSlots;
        initMouseListeners();
    }

    private void initMouseListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int slot = getSlotAt(e.getX(), e.getY());
                if (slot != -1) {
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        draggedSlot = slot;
                        draggedItem = InventoryManager.getSlot(draggedSlot).getItem();
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        // Rechtsklick: Gegenstand löschen (bzw. auf dem Spielfeld droppen)
                    	ItemTemplate item = InventoryManager.getSlot(getSlotAt(e.getX(), e.getY())).getItem();
                    	item.stackSize = InventoryManager.getSlot(getSlotAt(e.getX(), e.getY())).getCount();
                    	item.dropChance = 1;
                        if (item != null) {
                            LootTableTemplate dropItem = new LootTableTemplate(1, new ItemTemplate[]{item});
                            SpawnLoot.at(GameLogic.player.posX, GameLogic.player.posY, dropItem);
                            InventoryManager.setSlot(new Pair<>(new EmptyItem(0), 0), slot);
                            repaint();
                        }
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (draggedSlot != -1) {
                    int slot = getSlotAt(e.getX(), e.getY());
                    if (slot != -1 && slot != draggedSlot) {
                        // Gegenstand verschieben
                        Pair<ItemTemplate, Integer> targetSlot = InventoryManager.getSlot(slot);
                        InventoryManager.setSlot(InventoryManager.getSlot(draggedSlot), slot);
                        InventoryManager.setSlot(targetSlot, draggedSlot);
                    }
                    draggedSlot = -1;
                    draggedItem = null;
                    repaint();
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (draggedItem != null) {
                    repaint();
                }
            }
        });
    }

    private int getSlotAt(int x, int y) {
        int startX = (getWidth() / 100) * 15;
        int startY = (getHeight() / 100) * 16;
        for (int i = 0; i < SLOT_ROWS; i++) {
            for (int j = 0; j < SLOT_COLUMNS; j++) {
                int slotX = startX + j * SLOT_PADDING;
                int slotY = startY + i * SLOT_PADDING;
                if (x >= slotX && x <= slotX + SLOT_SIZE && y >= slotY && y <= slotY + SLOT_SIZE) {
                    int slotIndex = i * SLOT_COLUMNS + j;
                    if (slotIndex < maxInventarSlots) {
                        return slotIndex;
                    }
                }
            }
        }
        return -1;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!isVisible()) {
            return;
        }
        paintUniversal((Graphics2D) g);
        if (craftingTab) {
            paintSimpleCrafting((Graphics2D) g);
        } else {
            paintInventory((Graphics2D) g);
        }

        if (draggedItem != null) {
            drawDraggedItem((Graphics2D) g);
        }
    }

    private void paintInventory(Graphics2D g) {
        int posX = (getWidth() / 100) * 15;
        int posY = (getHeight() / 100) * 16;
        int currentInventarSlots = 0;

        // slots background
        g.setColor(Color.gray.darker());
        g.fillRoundRect((getWidth() / 100) * 14, (getHeight() / 100) * 14, 373, 575, 20, 20);
        g.setColor(Color.black);
        g.drawRoundRect((getWidth() / 100) * 14, (getHeight() / 100) * 14, 373, 575, 20, 20);

        // Inventar Slots
        for (int i = 0; i < SLOT_ROWS; i++) {
            for (int j = 0; j < SLOT_COLUMNS; j++) {
                if (currentInventarSlots >= maxInventarSlots) {
                    break;
                }
                g.setColor(Color.gray);
                g.fillRect(posX, posY, SLOT_SIZE, SLOT_SIZE);
                g.setColor(Color.black);
                g.drawRect(posX, posY, SLOT_SIZE, SLOT_SIZE);
                g.drawImage(InventoryManager.getItemImage(currentInventarSlots), posX + 5, posY + 5, 50, 50, null);
                if (!InventoryManager.isSlotEmpty(currentInventarSlots)) {
                    g.setColor(Color.white);
                    g.setFont(new Font("Tahoma", Font.BOLD, 12));
                    g.drawString(InventoryManager.getItemCount(currentInventarSlots) + "", posX + 40, posY + 50);
                }
                posX += SLOT_PADDING;
                currentInventarSlots++;
            }
            posY += SLOT_PADDING;
            posX = (getWidth() / 100) * 15;
        }

        // player space
        g.setColor(Color.gray.darker());
        g.fillRoundRect((getWidth() / 100) * 55, (getHeight() / 100) * 14, 425, 575, 20, 20);
        g.setColor(Color.black);
        g.drawRoundRect((getWidth() / 100) * 55, (getHeight() / 100) * 14, 425, 575, 20, 20);
        g.fillRoundRect((getWidth() / 100) * 70, (getHeight() / 100) * 35, 250, 250, 20, 20);

        // player
        g.setColor(Color.white);
        g.fillRect((getWidth() / 100) * 80, (getHeight() / 100) * 60, 50, 50);
    }

    private void paintSimpleCrafting(Graphics2D g) {
        // Implementiere das Zeichnen der Crafting-Tabelle hier
    }

    private void paintUniversal(Graphics2D g) {
        // background
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, getWidth(), getHeight());

        // basic Frame
        g.setColor(new Color(62, 62, 62, 150));
        g.fillRoundRect(getWidth() / 10, getHeight() / 10, (getWidth() / 10) * 8, (getHeight() / 10) * 8, 40, 40);
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(5));
        g.drawRoundRect(getWidth() / 10, getHeight() / 10, (getWidth() / 10) * 8, (getHeight() / 10) * 8, 40, 40);
    }

    private void drawDraggedItem(Graphics2D g) {
        if (draggedItem != null) {
            int mouseX = getMousePosition().x;
            int mouseY = getMousePosition().y;
            g.drawImage(draggedItem.itemImage, mouseX - SLOT_SIZE / 2, mouseY - SLOT_SIZE / 2, SLOT_SIZE, SLOT_SIZE, null);
        }
    }

    public void setMaxInventarSlots(int maxInventarSlots) {
        this.maxInventarSlots = maxInventarSlots;
    }
}
