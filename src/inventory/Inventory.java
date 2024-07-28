package inventory;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

import action.Logger;
import components.CustomeGraphics;
import game.GameLogic;
import loot.LootTableTemplate;
import loot.SpawnLoot;
import loot.items.EmptyItem;
import loot.items.ItemTemplate;
import rendering.Resources;
import test.SpriteTest;

public class Inventory extends JLabel {
    private static final long serialVersionUID = 1L;
    private int Interface = 0;
    public int maxInventarSlots;
    private static final int SLOT_SIZE = 60;
    private static final int SLOT_PADDING = 70;
    private static final int SLOT_ROWS = 8;
    private static final int SLOT_COLUMNS = 5;

    private static final Color BUTTON_COLOR_UNPRESSED = Color.gray;
    private static final Color BUTTON_COLOR_PRESSED = new Color(34, 139, 34);

    private int draggedSlot = -1;
    private ItemTemplate draggedItem = null;

    private int button1X, button1Y, button1Width, button1Height;
    private int button2X, button2Y, button2Width, button2Height;
    private int button3X, button3Y, button3Width, button3Height;

    public Inventory(int maxInventarSlots) {
        this.maxInventarSlots = maxInventarSlots;
        setLayout(null); // Ensure we are using absolute positioning
        initMouseListeners();
    }

    private void initMouseListeners() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (Interface == 0) {
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
                                LootTableTemplate dropItem = new LootTableTemplate(1, new ItemTemplate[]{item}, "simgle Item");
                                SpawnLoot.at(GameLogic.player.posX, GameLogic.player.posY, dropItem);
                                InventoryManager.setSlot(new Pair<>(new EmptyItem(0), 0), slot);
                                repaint();
                            }
                        }
                    }
                }

                if (Interface == 1) {
                    // Platz für zusätzliche Logik für Interface 1
                }

                // Prüfe auf Button-Klicks
                if (isWithinButton(e.getX(), e.getY(), button1X, button1Y, button1Width, button1Height)) {
                    Interface = 0;
                    repaint();
                } else if (isWithinButton(e.getX(), e.getY(), button2X, button2Y, button2Width, button2Height)) {
                    Interface = 1;
                    repaint();
                } else if (isWithinButton(e.getX(), e.getY(), button3X, button3Y, button3Width, button3Height)) {
                    Interface = 2;
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (Interface == 0) {
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
                    int slot = getSlotAt(e.getX(), e.getY());
                    if (slot != -1) {
                        if (e.getButton() == MouseEvent.BUTTON2) {
                            ItemTemplate item = InventoryManager.getSlot(getSlotAt(e.getX(), e.getY())).getItem();
                            if (item.interactable && item.canInteract()) {
                                item.onInteraction();
                                int count = InventoryManager.getSlot(getSlotAt(e.getX(), e.getY())).getCount();
                                if (count > 1) {
                                    InventoryManager.getSlot(getSlotAt(e.getX(), e.getY())).setCount(count - 1);
                                } else {
                                    InventoryManager.setSlot(new Pair<>(new EmptyItem(0), 0), slot);
                                }
                            }
                        }
                    }
                }

                if (Interface == 1) {
                    // Platz für zusätzliche Logik für Interface 1
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
        addComponentListener(new ComponentListener() {
			@Override
			public void componentShown(ComponentEvent e) {}
			@Override
			public void componentResized(ComponentEvent e) {}
			@Override
			public void componentMoved(ComponentEvent e) {}
			@Override
			public void componentHidden(ComponentEvent e) {
				Interface = 0;
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
        if (Interface == 0) {
            paintInventory((Graphics2D) g);
        } else if (Interface == 1) {
            paintSimpleCrafting((Graphics2D) g);
        } else {
            paintQuestLog((Graphics2D) g);
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
        g.drawImage(Resources.player, (getWidth() / 100) * 72, (getHeight() / 100) * 45, 200, 200, null);
    }

    private void paintSimpleCrafting(Graphics2D g) {
        // Implementiere das Zeichnen der Crafting-Tabelle hier
    }

    private void paintQuestLog(Graphics2D g) {
        // Implementiere das Zeichnen der Crafting-Tabelle hier
    }

    private void paintUniversal(Graphics2D g) {
        // background
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, getWidth(), getHeight());

        // basic Frame
        int width = (getWidth() / 10) * 8;
        g.setColor(new Color(62, 62, 62, 150));
        g.fillRoundRect(getWidth() / 10, getHeight() / 16, width, (getHeight() / 100) * 91, 40, 40);
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(5));
        g.drawRoundRect(getWidth() / 10, getHeight() / 16, width, (getHeight() / 100) * 91, 40, 40);
        g.setStroke(new BasicStroke(6));
        g.drawRoundRect(getWidth() / 10, getHeight() / 16, width, (getHeight() / 100) * 5, 40, 40);
        g.setStroke(new BasicStroke(5));
        // Buttons
        int height = (int) ((getHeight() / 100) * 5);
        int posY = getHeight() / 16;
        int buttonWidth = width / 3;

        button1X = getWidth() / 10;
        button1Y = posY;
        button1Width = buttonWidth;
        button1Height = height;

        button2X = button1X + buttonWidth;
        button2Y = posY;
        button2Width = buttonWidth;
        button2Height = height;

        button3X = button2X + buttonWidth;
        button3Y = posY;
        button3Width = buttonWidth;
        button3Height = height;
        
        if (Interface == 0) g.setColor(BUTTON_COLOR_PRESSED); else g.setColor(BUTTON_COLOR_UNPRESSED);
        g.fill(CustomeGraphics.createRoundedLeftCorners(button1X+2, button1Y, button1Width-2, button1Height, 15));
        g.setColor(Color.black);
        g.draw(CustomeGraphics.createRoundedLeftCorners(button1X+2, button1Y, button1Width-2, button1Height, 15));
        if (Interface == 1) g.setColor(BUTTON_COLOR_PRESSED); else g.setColor(BUTTON_COLOR_UNPRESSED);
        g.fillRect(button2X, button2Y, button2Width, button2Height);
        g.setColor(Color.black);
        g.drawRect(button2X, button2Y, button2Width, button2Height);
        if (Interface == 2) g.setColor(BUTTON_COLOR_PRESSED); else g.setColor(BUTTON_COLOR_UNPRESSED);
        g.fill(CustomeGraphics.createRoundedRightCorners(button3X, button3Y, button3Width-2, button3Height, 15));
        g.setColor(Color.black);
        g.draw(CustomeGraphics.createRoundedRightCorners(button3X, button3Y, button3Width-2, button3Height, 15));
        
        //Button Strings
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString("Inventar", (int) (button1X+button1Width/2.75), (int) (button1Y+button1Height/1.5));
        g.drawString("Crafting", (int) (button2X+button2Width/2.75), (int) (button2Y+button2Height/1.5));
        g.drawString("Quest Log", (int) (button3X+button3Width/2.75), (int) (button3Y+button3Height/1.5));
    }

    private boolean isWithinButton(int mouseX, int mouseY, int buttonX, int buttonY, int buttonWidth, int buttonHeight) {
        return mouseX >= buttonX && mouseX <= (buttonX + buttonWidth) &&
               mouseY >= buttonY && mouseY <= (buttonY + buttonHeight);
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
