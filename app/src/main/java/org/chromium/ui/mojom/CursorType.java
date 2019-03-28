
// Copyright 2014 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

// This file is autogenerated by:
//     mojo/public/tools/bindings/mojom_bindings_generator.py
// For:
//     ui/base/mojo/cursor.mojom
//

package org.chromium.ui.mojom;

import org.chromium.mojo.bindings.DeserializationException;

public final class CursorType {


    public static final int NULL = (int) (0L);

    public static final int POINTER = NULL + 1;

    public static final int CROSS = POINTER + 1;

    public static final int HAND = CROSS + 1;

    public static final int I_BEAM = HAND + 1;

    public static final int WAIT = I_BEAM + 1;

    public static final int HELP = WAIT + 1;

    public static final int EAST_RESIZE = HELP + 1;

    public static final int NORTH_RESIZE = EAST_RESIZE + 1;

    public static final int NORTH_EAST_RESIZE = NORTH_RESIZE + 1;

    public static final int NORTH_WEST_RESIZE = NORTH_EAST_RESIZE + 1;

    public static final int SOUTH_RESIZE = NORTH_WEST_RESIZE + 1;

    public static final int SOUTH_EAST_RESIZE = SOUTH_RESIZE + 1;

    public static final int SOUTH_WEST_RESIZE = SOUTH_EAST_RESIZE + 1;

    public static final int WEST_RESIZE = SOUTH_WEST_RESIZE + 1;

    public static final int NORTH_SOUTH_RESIZE = WEST_RESIZE + 1;

    public static final int EAST_WEST_RESIZE = NORTH_SOUTH_RESIZE + 1;

    public static final int NORTH_EAST_SOUTH_WEST_RESIZE = EAST_WEST_RESIZE + 1;

    public static final int NORTH_WEST_SOUTH_EAST_RESIZE = NORTH_EAST_SOUTH_WEST_RESIZE + 1;

    public static final int COLUMN_RESIZE = NORTH_WEST_SOUTH_EAST_RESIZE + 1;

    public static final int ROW_RESIZE = COLUMN_RESIZE + 1;

    public static final int MIDDLE_PANNING = ROW_RESIZE + 1;

    public static final int EAST_PANNING = MIDDLE_PANNING + 1;

    public static final int NORTH_PANNING = EAST_PANNING + 1;

    public static final int NORTH_EAST_PANNING = NORTH_PANNING + 1;

    public static final int NORTH_WEST_PANNING = NORTH_EAST_PANNING + 1;

    public static final int SOUTH_PANNING = NORTH_WEST_PANNING + 1;

    public static final int SOUTH_EAST_PANNING = SOUTH_PANNING + 1;

    public static final int SOUTH_WEST_PANNING = SOUTH_EAST_PANNING + 1;

    public static final int WEST_PANNING = SOUTH_WEST_PANNING + 1;

    public static final int MOVE = WEST_PANNING + 1;

    public static final int VERTICAL_TEXT = MOVE + 1;

    public static final int CELL = VERTICAL_TEXT + 1;

    public static final int CONTEXT_MENU = CELL + 1;

    public static final int ALIAS = CONTEXT_MENU + 1;

    public static final int PROGRESS = ALIAS + 1;

    public static final int NO_DROP = PROGRESS + 1;

    public static final int COPY = NO_DROP + 1;

    public static final int NONE = COPY + 1;

    public static final int NOT_ALLOWED = NONE + 1;

    public static final int ZOOM_IN = NOT_ALLOWED + 1;

    public static final int ZOOM_OUT = ZOOM_IN + 1;

    public static final int GRAB = ZOOM_OUT + 1;

    public static final int GRABBING = GRAB + 1;

    public static final int CUSTOM = GRABBING + 1;

    public static final int MIN_VALUE = (int) (0);
    public static final int MAX_VALUE = (int) (44);

    private static final boolean IS_EXTENSIBLE = false;

    public static boolean isKnownValue(int value) {
        switch (value) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
                return true;
        }
        return false;
    }

    public static void validate(int value) {
        if (IS_EXTENSIBLE || isKnownValue(value))
            return;

        throw new DeserializationException("Invalid enum value.");
    }

    private CursorType() {}

}