package com.prafull.ArthTrack.model.updateList;

import java.util.List;

import com.prafull.ArthTrack.model.detailModel.FriendDetailModel;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendModel {
    private List<FriendDetailModel> friends;
}
